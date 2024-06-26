package darecreek.vfuAutotest.fullPipeline

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3._
import chiseltest.WriteVcdAnnotation
import scala.reflect.io.File
import scala.reflect.runtime.universe._
import scala.collection.mutable.Map
import scala.util.Random

import darecreek.exu.vfu._
import darecreek.exu.vfu.alu._
import darecreek.exu.vfu.VInstructions._
import darecreek.exu.vfu.perm._
import darecreek.exu.vfu.fp._
import darecreek.exu.vfu.div._
import darecreek.exu.vfu.vmask._
import darecreek.exu.vfu.reduction._
import chipsalliance.rocketchip.config.Parameters
import scala.util.control.Breaks._

class DivTestEngine extends TestEngine {

    override def getName() = "DivTestEngine"
    override def getDut() = new VDiv

    var historyTCs : List[(Int, Int, TestCase)] = List() // robIdx, uopIdx, TestCase
    var historyTCIx = 0

    var results : List[(Boolean, Int, Int)] = List() // correctness, robIdx, uopIdx

    def clearFlushedRes(robIdx : Int) = {
        results = results.filter(_._2 > robIdx) // flush compare
    }

    def checkOutput(dut : VDiv, enableRandomBlock : Boolean = true) = {
        var block = randomBlock()
        if (!enableRandomBlock) block = false
        dut.io.out.ready.poke((!block).B) // TODO randomly block

        println(s".. checkOutput block = ${block}, ready = ${!block}")

        if (!block && dut.io.out.valid.peek().litValue == 1) {

            println(".. valid == true, checking results..")

            println("historyTCs:")
            historyTCs.foreach(x => {
                println(s".. robIdx ${x._1}, uopIdx ${x._2}")
            })
            
            var robIdx = dut.io.out.bits.uop.sysUop.robIdx.value.peek().litValue.toInt
            var uopIdx = dut.io.out.bits.uop.uopIdx.peek().litValue.toInt

            // ========
            /*while(
                historyTCs.length > 0 &&
                // && (historyTCs(0)._1 != robIdx || historyTCs(0)._2 != uopIdx)
                historyTCs(0)._3.flushed
            ) {
                if (historyTCs(0)._1 == robIdx) {
                    println(s"ERROR!!!!!! Received flushed result for robIdx ${robIdx}, uop ${uopIdx}!!!!!!")
                    assert(false)
                }
                historyTCs = historyTCs.tail
            }

            if (historyTCs.length == 0) {
                println(s"historyTCs is empty!!!!!!!!")
                assert(false)
            }*/
            // ========

            val filteredCandids = historyTCs.filter(x => {x._1 == robIdx && x._2 == uopIdx})
            historyTCs = historyTCs.filter(x => { x._1 != robIdx || x._2 != uopIdx })

            /*println("historyTCs:")
            historyTCs.foreach(x => {
                println(s".. robIdx ${x._1}, uopIdx ${x._2}")
            })*/

            assert(filteredCandids.length != 0, s"ERROR!!!!!! (flushed?) Not expecting robIdx ${robIdx}, uopIdx ${uopIdx}")
            assert(filteredCandids.length == 1, s"ERROR!!!!!! Waiting for multiple outputs of robIdx ${robIdx}, uopIdx ${uopIdx}")

            val resRobIdx = filteredCandids(0)._1
            val resUopIdx = filteredCandids(0)._2
            val resTestCase = filteredCandids(0)._3
            val testCaseFlushed = resTestCase.flushed
            assert(!testCaseFlushed, s"ERROR!!!!!! Flushed before! robIdx ${resRobIdx}, uopIdx ${resUopIdx}")

            // historyTCs = historyTCs.tail
            
            println(s"2.2. Received result for robIdx ${robIdx}, uopIdx ${uopIdx}, in DivTestEngine:")
            if (testCaseFlushed) {
                println(".. 2.2.1. flushed! so not comparing")
            } else {
                val cb = resTestCase.getCtrlBundleByUopIdx(resUopIdx)
                val uop = genVFuUop(cb)
                dut.io.out.bits.uop.expect(uop)

                val dutVd = dut.io.out.bits.vd.peek().litValue
                val fflags = dut.io.out.bits.fflags.peek().litValue.toInt

                val resCorrectness = resTestCase.rc.checkRes(dutVd, uopIdx, dutFflags=fflags)
                val resRobIdx = robIdx

                results :+= (resCorrectness, resRobIdx, resUopIdx)
            }
        }
    }

    override def iterate(
        dut : VDiv, chosenTestCase : TestCase, 
        sendRobIdx : Int, allExhausted : Boolean, 
        flush : Boolean, flushedRobIdx : Int
    ) : (Boolean, Int) = {

        val MAX_READY_WAIT = 200
        var curReadyWait = 0
        
        if (!allExhausted) {
            val (input, uopIdx) : (VFuInput, Int) = chosenTestCase.nextVfuInput((false, sendRobIdx))
            println(s"1. Sending ${chosenTestCase.instid}, robIdx ${sendRobIdx}, uop ${uopIdx}/(0-${chosenTestCase.ctrlBundles.length - 1})")
            // ===================== manipulating dut ========================

            dut.io.in.valid.poke(true.B) // TODO randomly block

            // sending input ====================================
            // dut.io.dontCare.poke(false.B)
            
            dut.io.in.bits.poke(input)
            if (flush) {
                dut.io.redirect.poke(genFSMRedirect(flush, flush, flushedRobIdx))
            } else {
                dut.io.redirect.poke(genFSMRedirect())
            }

            // in the same cycle, before marking flushed instructions, check output
            //  If there's any, the historyTCs item will be removed
            //  and one result will be added
            checkOutput(dut)

            // Tag test cases' uops that should be flushed
            // and no vd received yet
            if (flush) {
                for (i <- 0 until historyTCs.length) {
                    if(historyTCs(i)._1 <= flushedRobIdx) { // flush compare
                        println(s".. flush robIdx ${historyTCs(i)._1}, uopIdx ${historyTCs(i)._2}")
                        historyTCs(i)._3.flush()
                    }
                }

                historyTCs = historyTCs.filter(!_._3.flushed)

                // clear past results of test case with less robIdx
                clearFlushedRes(flushedRobIdx)
                println(s"2. Flushed (all <= ${flushedRobIdx}), from DivTestEngine")
                // dut.clock.step(1)
                // dut.io.redirect.poke(genFSMRedirect())
            }

            var jumpBeforeStep = false
            // waiting for dut's ready signal, which represents an ack of the uop ========
            breakable { while((dut.io.in.ready.peek().litValue != 1) &&
                    curReadyWait < MAX_READY_WAIT) {
                
                if (curReadyWait != 0) checkOutput(dut) // might affact in.ready
                if (dut.io.in.ready.peek().litValue == 1) {
                    jumpBeforeStep = true
                    break
                }
                dut.clock.step(1)
                dut.io.redirect.poke(genFSMRedirect())
                curReadyWait += 1
            } }

            // waits too long.. =====================================
            if (!(curReadyWait < MAX_READY_WAIT)) {
                println(s"ERROR!!! no io.ready signal received")
                assert(false)
            }
            assert(curReadyWait < MAX_READY_WAIT)

            // dut.io.in.valid.poke(false.B)

            if (
                chosenTestCase.rc.n_ops != 1 ||
                (chosenTestCase.rc.n_ops == 1 && chosenTestCase.isExhausted())
            ) {
                historyTCs :+= (sendRobIdx, uopIdx, chosenTestCase)
            }

            if (curReadyWait > 0 && !jumpBeforeStep) {
                // Here, the Engine passed through the while loop,
                //  clock ticked inside it
                //  then one should check one more time for the result
                
                // if the Engine didn't enter the while loop (curReadyWait == 0),
                //  then the engine didn't tick after "sending the input and checking
                //  the result for the first time".
                //  then here we should not check for the result second time.
                // dut.io.in.valid.poke(false.B)
                checkOutput(dut, false)
            }
            dut.clock.step(1)
        } else {
            dut.io.in.valid.poke(false.B)
            dut.io.in.bits.uop.uopEnd.poke(false.B)

            // dut.io.dontCare.poke(true.B)
            dut.io.in.bits.poke(getEmptyVFuInput()) // don't care..
            if (flush) {
                dut.io.redirect.poke(genFSMRedirect(flush, flush, flushedRobIdx))
            } else {
                dut.io.redirect.poke(genFSMRedirect())
            }

            checkOutput(dut)

            if (flush) {
                for (i <- 0 until historyTCs.length) {
                    if(historyTCs(i)._1 <= flushedRobIdx) { // flush compare
                        println(s".. flush robIdx ${historyTCs(i)._1}, uopIdx ${historyTCs(i)._2}")
                        historyTCs(i)._3.flush()
                    }
                }

                historyTCs = historyTCs.filter(!_._3.flushed)

                // clear past results of test case with less robIdx
                clearFlushedRes(flushedRobIdx)
                println(s"2. Flushed (all <= ${flushedRobIdx}), from DivTestEngine")
            }
            // dut.io.redirect.poke(genFSMRedirect())

            dut.clock.step(1)
        }

        // TODO 1.3. check for potential results, get the comparison result
        // val resCorrectness = chosenTestCase.rc.checkRes(dutVd, uopIdx, dutVxsat=dutVxsat)
        // val resRobIdx = sendRobIdx
        if (results.length > 0) {
            val resCorrectness = results(0)._1
            val resRobIdx = results(0)._2
            val resUopIdx = results(0)._3
            results = results.tail
            println(s"Returning robIdx ${resRobIdx}, uop ${resUopIdx}'s result: ${resCorrectness}.")
            return (resCorrectness, resRobIdx)
        }
        return (false, NO_RESULT_ROBIDX)
    }

}