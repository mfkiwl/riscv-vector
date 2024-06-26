verilog:
	sbt "runMain darecreek.Main" -mem 4096

test_alu:
	sbt "testOnly darecreek.vfutest.alu.VAluSpec"

test_mac:
	sbt "testOnly darecreek.vfutest.alu.VMacSpec"

test_div:
	sbt "testOnly darecreek.vfutest.alu.VDivSpec"

test_load:
	sbt "testOnly darecreek.lsutest.VLsuSpec_ld"

test_load_idxSeg:
	sbt "testOnly darecreek.lsutest.VLsuSpec_ld_idxSeg"

test_store:
	sbt "testOnly darecreek.lsutest.VLsuSpec_st"

test_amd: test_alu test_mac test_div
test_ldst: test_load test_load_idxSeg test_store


clean:
	rm -rf ./build;	rm -rf ./generated;	rm -rf ./test_run_dir

.PHONY: verilog test_alu test_mac test_div test_amd test_load test_load_idxSeg test_store \
        clean
