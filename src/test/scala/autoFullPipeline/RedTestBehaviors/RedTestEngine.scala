package darecreek.vfuAutotest.fullPipeline

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3._
import chiseltest.WriteVcdAnnotation
import scala.reflect.io.File
import scala.reflect.runtime.universe._
import scala.collection.mutable.Map

import darecreek.exu.vfu._
import darecreek.exu.vfu.alu._
import darecreek.exu.vfu.vmask._
import darecreek.exu.vfu.reduction._
import darecreek.exu.vfu.VInstructions._

class RedTestEngine extends TestEngine {

    override def getName() = "RedTestEngine"
    override def getDut() = new Reduction

    var historyTCs : List[(Int, Int, TestCase)] = 
        List() // robIdx, uopIdx, TestCase, flushed
    var historyTCIx = 0

    var results : List[(Boolean, Int)] = List()

    def clearFlushedRes(robIdx : Int) = {
        results = results.filter(_._2 > robIdx) // flush compare
    }

    def checkOutput(dut : Reduction) = {
        if (dut.io.out.valid.peek().litValue == 1) {

            val robIdx = historyTCs(0)._1
            val uopIdx = historyTCs(0)._2
            val resTestCase = historyTCs(0)._3
            val testCaseFlushed = resTestCase.flushed
            historyTCs = historyTCs.tail

            val dutVd = dut.io.out.bits.vd.peek().litValue

            println(s"2.2. Received result, Comparing with ${resTestCase.instid} robIdx ${robIdx}, uopIdx ${uopIdx}, in RedTestEngine:")

            val resCorrectness = resTestCase.rc.checkRes(dutVd, uopIdx)
            val resRobIdx = robIdx

            if (testCaseFlushed) {
                println(".. 2.2.1. flushed! so not comparing")
            } else {
                results :+= (resCorrectness, resRobIdx)
            }
        }
    }


    override def iterate(
        dut : Reduction, chosenTestCase : TestCase, 
        sendRobIdx : Int, allExhausted : Boolean, 
        flush : Boolean, flushedRobIdx : Int
    ) : (Boolean, Int) = {

        // ===================== manipulating dut ========================
        if(!allExhausted) {
            val (input, uopIdx) : (VFuInput, Int) = chosenTestCase.nextVfuInput((false, sendRobIdx))
            println(s"2. Sending ${chosenTestCase.instid}, uop ${uopIdx}, robIdx ${sendRobIdx}")

            dut.io.in.valid.poke(true.B)

            dut.io.in.bits.poke(input)

            if (flush) {
                for (i <- 0 until historyTCs.length) {
                    if(historyTCs(i)._1 <= flushedRobIdx) { // flush compare
                        historyTCs(i)._3.flush()
                    }
                }
            }

            // Only check for the result after the last uop
            if (chosenTestCase.isExhausted()) 
                historyTCs :+= (sendRobIdx, uopIdx, chosenTestCase)

            // TestEngine (outside) needs not know the flushed result.
            clearFlushedRes(flushedRobIdx)
        }

        dut.clock.step(1)

        dut.io.in.valid.poke(false.B)
        // dut.io.redirect.poke(genFSMRedirect())
        checkOutput(dut)

        if (results.length > 0) {
            val resCorrectness = results(0)._1
            val resRobIdx = results(0)._2
            results = results.tail
            return (resCorrectness, resRobIdx)
        }

        return (false, NO_RESULT_ROBIDX)
    }

}