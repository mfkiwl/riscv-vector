package darecreek.vfuAutotest.fullPipeline

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.{FunSuite, ParallelTestExecution}
import chisel3._
import darecreek.exu.vfu._
import darecreek.exu.vfu.alu._
import darecreek.exu.vfu.VInstructions._
import chiseltest.WriteVcdAnnotation
import scala.reflect.runtime.universe._
import scala.collection.mutable.Map
import scala.io.Source
import java.io.FileWriter
import java.time.{LocalDate, LocalDateTime}
import java.nio.file.{Paths, Files}
import java.time.format.DateTimeFormatter
import java.io._
import scala.concurrent._
import scala.concurrent.duration._
import scala.util._
import ExecutionContext.Implicits.global
import scala.collection.concurrent.{Map => ConCurMap}
import scala.collection.mutable.ListBuffer
import scala.collection.convert.decorateAsScala._
import java.util.concurrent.ConcurrentHashMap



object ctrlBundles {
  // Vector Load/Store instructions
  val vle8_v = CtrlBundle(VLE8_V)
  val vle16_v = CtrlBundle(VLE16_V)
  val vle32_v = CtrlBundle(VLE32_V)
  val vle64_v = CtrlBundle(VLE64_V)
  val vse8_v = CtrlBundle(VSE8_V)
  val vse16_v = CtrlBundle(VSE16_V)
  val vse32_v = CtrlBundle(VSE32_V)
  val vse64_v = CtrlBundle(VSE64_V)
  val vlm_v = CtrlBundle(VLM_V)
  val vsm_v = CtrlBundle(VSM_V)
  val vlse8_v = CtrlBundle(VLSE8_V)
  val vlse16_v = CtrlBundle(VLSE16_V)
  val vlse32_v = CtrlBundle(VLSE32_V)
  val vlse64_v = CtrlBundle(VLSE64_V)
  val vsse8_v = CtrlBundle(VSSE8_V)
  val vsse16_v = CtrlBundle(VSSE16_V)
  val vsse32_v = CtrlBundle(VSSE32_V)
  val vsse64_v = CtrlBundle(VSSE64_V)
  val vluxei8_v = CtrlBundle(VLUXEI8_V)
  val vluxei16_v = CtrlBundle(VLUXEI16_V)
  val vluxei32_v = CtrlBundle(VLUXEI32_V)
  val vluxei64_v = CtrlBundle(VLUXEI64_V)
  val vloxei8_v = CtrlBundle(VLOXEI8_V)
  val vloxei16_v = CtrlBundle(VLOXEI16_V)
  val vloxei32_v = CtrlBundle(VLOXEI32_V)
  val vloxei64_v = CtrlBundle(VLOXEI64_V)
  val vsuxei8_v = CtrlBundle(VSUXEI8_V)
  val vsuxei16_v = CtrlBundle(VSUXEI16_V)
  val vsuxei32_v = CtrlBundle(VSUXEI32_V)
  val vsuxei64_v = CtrlBundle(VSUXEI64_V)
  val vsoxei8_v = CtrlBundle(VSOXEI8_V)
  val vsoxei16_v = CtrlBundle(VSOXEI16_V)
  val vsoxei32_v = CtrlBundle(VSOXEI32_V)
  val vsoxei64_v = CtrlBundle(VSOXEI64_V)
  val vle8ff_v = CtrlBundle(VLE8FF_V)
  val vle16ff_v = CtrlBundle(VLE16FF_V)
  val vle32ff_v = CtrlBundle(VLE32FF_V)
  val vle64ff_v = CtrlBundle(VLE64FF_V)
  val vl1re8_v = CtrlBundle(VL1RE8_V)
  val vl1re16_v = CtrlBundle(VL1RE16_V)
  val vl1re32_v = CtrlBundle(VL1RE32_V)
  val vl1re64_v = CtrlBundle(VL1RE64_V)
  val vl2re8_v = CtrlBundle(VL2RE8_V)
  val vl2re16_v = CtrlBundle(VL2RE16_V)
  val vl2re32_v = CtrlBundle(VL2RE32_V)
  val vl2re64_v = CtrlBundle(VL2RE64_V)
  val vl4re8_v = CtrlBundle(VL4RE8_V)
  val vl4re16_v = CtrlBundle(VL4RE16_V)
  val vl4re32_v = CtrlBundle(VL4RE32_V)
  val vl4re64_v = CtrlBundle(VL4RE64_V)
  val vl8re8_v = CtrlBundle(VL8RE8_V)
  val vl8re16_v = CtrlBundle(VL8RE16_V)
  val vl8re32_v = CtrlBundle(VL8RE32_V)
  val vl8re64_v = CtrlBundle(VL8RE64_V)
  val vs1r_v = CtrlBundle(VS1R_V)
  val vs2r_v = CtrlBundle(VS2R_V)
  val vs4r_v = CtrlBundle(VS4R_V)
  val vs8r_v = CtrlBundle(VS8R_V)

  // Vector Integer Arithmetic Instructions
  val vadd_vv = CtrlBundle(VADD_VV)
  val vadd_vx = CtrlBundle(VADD_VX)
  val vadd_vi = CtrlBundle(VADD_VI)
  val vsub_vv = CtrlBundle(VSUB_VV)
  val vsub_vx = CtrlBundle(VSUB_VX)
  val vrsub_vx = CtrlBundle(VRSUB_VX)
  val vrsub_vi = CtrlBundle(VRSUB_VI)
  val vwaddu_vv = CtrlBundle(VWADDU_VV)
  val vwaddu_vx = CtrlBundle(VWADDU_VX)
  val vwsubu_vv = CtrlBundle(VWSUBU_VV)
  val vwsubu_vx = CtrlBundle(VWSUBU_VX)
  val vwadd_vv = CtrlBundle(VWADD_VV)
  val vwadd_vx = CtrlBundle(VWADD_VX)
  val vwsub_vv = CtrlBundle(VWSUB_VV)
  val vwsub_vx = CtrlBundle(VWSUB_VX)
  val vwaddu_wv = CtrlBundle(VWADDU_WV)
  val vwaddu_wx = CtrlBundle(VWADDU_WX)
  val vwsubu_wv = CtrlBundle(VWSUBU_WV)
  val vwsubu_wx = CtrlBundle(VWSUBU_WX)
  val vwadd_wv = CtrlBundle(VWADD_WV)
  val vwadd_wx = CtrlBundle(VWADD_WX)
  val vwsub_wv = CtrlBundle(VWSUB_WV)
  val vwsub_wx = CtrlBundle(VWSUB_WX)
  val vzext_vf2 = CtrlBundle(VZEXT_VF2)
  val vsext_vf2 = CtrlBundle(VSEXT_VF2)
  val vzext_vf4 = CtrlBundle(VZEXT_VF4)
  val vsext_vf4 = CtrlBundle(VSEXT_VF4)
  val vzext_vf8 = CtrlBundle(VZEXT_VF8)
  val vsext_vf8 = CtrlBundle(VSEXT_VF8)
  val vadc_vvm = CtrlBundle(VADC_VVM)
  val vadc_vxm = CtrlBundle(VADC_VXM)
  val vadc_vim = CtrlBundle(VADC_VIM)
  val vmadc_vvm = CtrlBundle(VMADC_VVM)
  val vmadc_vxm = CtrlBundle(VMADC_VXM)
  val vmadc_vim = CtrlBundle(VMADC_VIM)
  val vmadc_vv = CtrlBundle(VMADC_VV)
  val vmadc_vx = CtrlBundle(VMADC_VX)
  val vmadc_vi = CtrlBundle(VMADC_VI)
  val vsbc_vvm = CtrlBundle(VSBC_VVM)
  val vsbc_vxm = CtrlBundle(VSBC_VXM)
  val vmsbc_vvm = CtrlBundle(VMSBC_VVM)
  val vmsbc_vxm = CtrlBundle(VMSBC_VXM)
  val vmsbc_vv = CtrlBundle(VMSBC_VV)
  val vmsbc_vx = CtrlBundle(VMSBC_VX)
  val vand_vv = CtrlBundle(VAND_VV)
  val vand_vx = CtrlBundle(VAND_VX)
  val vand_vi = CtrlBundle(VAND_VI)
  val vor_vv = CtrlBundle(VOR_VV)
  val vor_vx = CtrlBundle(VOR_VX)
  val vor_vi = CtrlBundle(VOR_VI)
  val vxor_vv = CtrlBundle(VXOR_VV)
  val vxor_vx = CtrlBundle(VXOR_VX)
  val vxor_vi = CtrlBundle(VXOR_VI)
  val vsll_vv = CtrlBundle(VSLL_VV)
  val vsll_vx = CtrlBundle(VSLL_VX)
  val vsll_vi = CtrlBundle(VSLL_VI)
  val vsrl_vv = CtrlBundle(VSRL_VV)
  val vsrl_vx = CtrlBundle(VSRL_VX)
  val vsrl_vi = CtrlBundle(VSRL_VI)
  val vsra_vv = CtrlBundle(VSRA_VV)
  val vsra_vx = CtrlBundle(VSRA_VX)
  val vsra_vi = CtrlBundle(VSRA_VI)
  val vnsrl_wv = CtrlBundle(VNSRL_WV)
  val vnsrl_wx = CtrlBundle(VNSRL_WX)
  val vnsrl_wi = CtrlBundle(VNSRL_WI)
  val vnsra_wv = CtrlBundle(VNSRA_WV)
  val vnsra_wx = CtrlBundle(VNSRA_WX)
  val vnsra_wi = CtrlBundle(VNSRA_WI)
  val vmseq_vv = CtrlBundle(VMSEQ_VV)
  val vmseq_vx = CtrlBundle(VMSEQ_VX)
  val vmseq_vi = CtrlBundle(VMSEQ_VI)
  val vmsne_vv = CtrlBundle(VMSNE_VV)
  val vmsne_vx = CtrlBundle(VMSNE_VX)
  val vmsne_vi = CtrlBundle(VMSNE_VI)
  val vmsltu_vv = CtrlBundle(VMSLTU_VV)
  val vmsltu_vx = CtrlBundle(VMSLTU_VX)
  val vmslt_vv = CtrlBundle(VMSLT_VV)
  val vmslt_vx = CtrlBundle(VMSLT_VX)
  val vmsleu_vv = CtrlBundle(VMSLEU_VV)
  val vmsleu_vx = CtrlBundle(VMSLEU_VX)
  val vmsleu_vi = CtrlBundle(VMSLEU_VI)
  val vmsle_vv = CtrlBundle(VMSLE_VV)
  val vmsle_vx = CtrlBundle(VMSLE_VX)
  val vmsle_vi = CtrlBundle(VMSLE_VI)
  val vmsgtu_vx = CtrlBundle(VMSGTU_VX)
  val vmsgtu_vi = CtrlBundle(VMSGTU_VI)
  val vmsgt_vx = CtrlBundle(VMSGT_VX)
  val vmsgt_vi = CtrlBundle(VMSGT_VI)
  val vminu_vv = CtrlBundle(VMINU_VV)
  val vminu_vx = CtrlBundle(VMINU_VX)
  val vmin_vv = CtrlBundle(VMIN_VV)
  val vmin_vx = CtrlBundle(VMIN_VX)
  val vmaxu_vv = CtrlBundle(VMAXU_VV)
  val vmaxu_vx = CtrlBundle(VMAXU_VX)
  val vmax_vv = CtrlBundle(VMAX_VV)
  val vmax_vx = CtrlBundle(VMAX_VX)

  val vmul_vv = CtrlBundle(VMUL_VV)
  val vmul_vx = CtrlBundle(VMUL_VX)
  val vmulh_vv = CtrlBundle(VMULH_VV)
  val vmulh_vx = CtrlBundle(VMULH_VX)
  val vmulhu_vv = CtrlBundle(VMULHU_VV)
  val vmulhu_vx = CtrlBundle(VMULHU_VX)
  val vmulhsu_vv = CtrlBundle(VMULHSU_VV)
  val vmulhsu_vx = CtrlBundle(VMULHSU_VX)
  val vwmul_vv = CtrlBundle(VWMUL_VV)
  val vwmul_vx = CtrlBundle(VWMUL_VX)
  val vwmulu_vv = CtrlBundle(VWMULU_VV)
  val vwmulu_vx = CtrlBundle(VWMULU_VX)
  val vwmulsu_vv = CtrlBundle(VWMULSU_VV)
  val vwmulsu_vx = CtrlBundle(VWMULSU_VX)
  val vmacc_vv = CtrlBundle(VMACC_VV)
  val vmacc_vx = CtrlBundle(VMACC_VX)
  val vnmsac_vv = CtrlBundle(VNMSAC_VV)
  val vnmsac_vx = CtrlBundle(VNMSAC_VX)
  val vmadd_vv = CtrlBundle(VMADD_VV)
  val vmadd_vx = CtrlBundle(VMADD_VX)
  val vnmsub_vv = CtrlBundle(VNMSUB_VV)
  val vnmsub_vx = CtrlBundle(VNMSUB_VX)
  val vwmaccu_vv = CtrlBundle(VWMACCU_VV)
  val vwmaccu_vx = CtrlBundle(VWMACCU_VX)
  val vwmacc_vv = CtrlBundle(VWMACC_VV)
  val vwmacc_vx = CtrlBundle(VWMACC_VX)
  val vwmaccsu_vv = CtrlBundle(VWMACCSU_VV)
  val vwmaccsu_vx = CtrlBundle(VWMACCSU_VX)
  val vwmaccus_vx = CtrlBundle(VWMACCUS_VX)
  val vmerge_vvm = CtrlBundle(VMERGE_VVM)
  val vmerge_vxm = CtrlBundle(VMERGE_VXM)
  val vmerge_vim = CtrlBundle(VMERGE_VIM)
  val vmv_v_v = CtrlBundle(VMV_V_V)
  val vmv_v_x = CtrlBundle(VMV_V_X)
  val vmv_v_i = CtrlBundle(VMV_V_I)

  // Vector Fixed-Point instructions
  val vsaddu_vv = CtrlBundle(VSADDU_VV)
  val vsaddu_vx = CtrlBundle(VSADDU_VX)
  val vsaddu_vi = CtrlBundle(VSADDU_VI)
  val vsadd_vv = CtrlBundle(VSADD_VV)
  val vsadd_vx = CtrlBundle(VSADD_VX)
  val vsadd_vi = CtrlBundle(VSADD_VI)
  val vssubu_vv = CtrlBundle(VSSUBU_VV)
  val vssubu_vx = CtrlBundle(VSSUBU_VX)
  val vssub_vv = CtrlBundle(VSSUB_VV)
  val vssub_vx = CtrlBundle(VSSUB_VX)
  val vaaddu_vv = CtrlBundle(VAADDU_VV)
  val vaaddu_vx = CtrlBundle(VAADDU_VX)
  val vaadd_vv = CtrlBundle(VAADD_VV)
  val vaadd_vx = CtrlBundle(VAADD_VX)
  val vasubu_vv = CtrlBundle(VASUBU_VV)
  val vasubu_vx = CtrlBundle(VASUBU_VX)
  val vasub_vv = CtrlBundle(VASUB_VV)
  val vasub_vx = CtrlBundle(VASUB_VX)
  val vsmul_vv = CtrlBundle(VSMUL_VV)
  val vsmul_vx = CtrlBundle(VSMUL_VX)
  val vssrl_vv = CtrlBundle(VSSRL_VV)
  val vssrl_vx = CtrlBundle(VSSRL_VX)
  val vssrl_vi = CtrlBundle(VSSRL_VI)
  val vssra_vv = CtrlBundle(VSSRA_VV)
  val vssra_vx = CtrlBundle(VSSRA_VX)
  val vssra_vi = CtrlBundle(VSSRA_VI)
  val vnclipu_wv = CtrlBundle(VNCLIPU_WV)
  val vnclipu_wx = CtrlBundle(VNCLIPU_WX)
  val vnclipu_wi = CtrlBundle(VNCLIPU_WI)
  val vnclip_wv = CtrlBundle(VNCLIP_WV)
  val vnclip_wx = CtrlBundle(VNCLIP_WX)
  val vnclip_wi = CtrlBundle(VNCLIP_WI)

  // Vector Floating-point instructions
  val vfadd_vv = CtrlBundle(VFADD_VV)
  val vfadd_vf = CtrlBundle(VFADD_VF)
  val vfsub_vv = CtrlBundle(VFSUB_VV)
  val vfsub_vf = CtrlBundle(VFSUB_VF)
  val vfrsub_vf = CtrlBundle(VFRSUB_VF)
  val vfwadd_vv = CtrlBundle(VFWADD_VV)
  val vfwadd_vf = CtrlBundle(VFWADD_VF)
  val vfwsub_vv = CtrlBundle(VFWSUB_VV)
  val vfwsub_vf = CtrlBundle(VFWSUB_VF)
  val vfwadd_wv = CtrlBundle(VFWADD_WV)
  val vfwadd_wf = CtrlBundle(VFWADD_WF)
  val vfwsub_wv = CtrlBundle(VFWSUB_WV)
  val vfwsub_wf = CtrlBundle(VFWSUB_WF)
  val vfmul_vv = CtrlBundle(VFMUL_VV)
  val vfmul_vf = CtrlBundle(VFMUL_VF)
  val vfdiv_vv = CtrlBundle(VFDIV_VV)
  val vfdiv_vf = CtrlBundle(VFDIV_VF)
  val vfrdiv_vf = CtrlBundle(VFRDIV_VF)
  val vfwmul_vv = CtrlBundle(VFWMUL_VV)
  val vfwmul_vf = CtrlBundle(VFWMUL_VF)
  val vfmacc_vv = CtrlBundle(VFMACC_VV)
  val vfmacc_vf = CtrlBundle(VFMACC_VF)
  val vfnmacc_vv = CtrlBundle(VFNMACC_VV)
  val vfnmacc_vf = CtrlBundle(VFNMACC_VF)
  val vfmsac_vv = CtrlBundle(VFMSAC_VV)
  val vfmsac_vf = CtrlBundle(VFMSAC_VF)
  val vfnmsac_vv = CtrlBundle(VFNMSAC_VV)
  val vfnmsac_vf = CtrlBundle(VFNMSAC_VF)
  val vfmadd_vv = CtrlBundle(VFMADD_VV)
  val vfmadd_vf = CtrlBundle(VFMADD_VF)
  val vfnmadd_vv = CtrlBundle(VFNMADD_VV)
  val vfnmadd_vf = CtrlBundle(VFNMADD_VF)
  val vfmsub_vv = CtrlBundle(VFMSUB_VV)
  val vfmsub_vf = CtrlBundle(VFMSUB_VF)
  val vfnmsub_vv = CtrlBundle(VFNMSUB_VV)
  val vfnmsub_vf = CtrlBundle(VFNMSUB_VF)
  val vfwmacc_vv = CtrlBundle(VFWMACC_VV)
  val vfwmacc_vf = CtrlBundle(VFWMACC_VF)
  val vfwnmacc_vv = CtrlBundle(VFWNMACC_VV)
  val vfwnmacc_vf = CtrlBundle(VFWNMACC_VF)
  val vfwmsac_vv = CtrlBundle(VFWMSAC_VV)
  val vfwmsac_vf = CtrlBundle(VFWMSAC_VF)
  val vfwnmsac_vv = CtrlBundle(VFWNMSAC_VV)
  val vfwnmsac_vf = CtrlBundle(VFWNMSAC_VF)
  val vfsqrt_v = CtrlBundle(VFSQRT_V)
  val vfrsqrt7_v = CtrlBundle(VFRSQRT7_V)
  val vfrec7_v = CtrlBundle(VFREC7_V)
  val vfmin_vv = CtrlBundle(VFMIN_VV)
  val vfmin_vf = CtrlBundle(VFMIN_VF)
  val vfmax_vv = CtrlBundle(VFMAX_VV)
  val vfmax_vf = CtrlBundle(VFMAX_VF)
  val vfsgnj_vv = CtrlBundle(VFSGNJ_VV)
  val vfsgnj_vf = CtrlBundle(VFSGNJ_VF)
  val vfsgnjn_vv = CtrlBundle(VFSGNJN_VV)
  val vfsgnjn_vf = CtrlBundle(VFSGNJN_VF)
  val vfsgnjx_vv = CtrlBundle(VFSGNJX_VV)
  val vfsgnjx_vf = CtrlBundle(VFSGNJX_VF)
  val vmfeq_vv = CtrlBundle(VMFEQ_VV)
  val vmfeq_vf = CtrlBundle(VMFEQ_VF)
  val vmfne_vv = CtrlBundle(VMFNE_VV)
  val vmfne_vf = CtrlBundle(VMFNE_VF)
  val vmflt_vv = CtrlBundle(VMFLT_VV)
  val vmflt_vf = CtrlBundle(VMFLT_VF)
  val vmfle_vv = CtrlBundle(VMFLE_VV)
  val vmfle_vf = CtrlBundle(VMFLE_VF)
  val vmfgt_vf = CtrlBundle(VMFGT_VF)
  val vmfge_vf = CtrlBundle(VMFGE_VF)
  val vfclass_v = CtrlBundle(VFCLASS_V)
  val vfmerge_vfm = CtrlBundle(VFMERGE_VFM)
  val vfmv_v_f = CtrlBundle(VFMV_V_F)
  val vfcvt_xu_f_v = CtrlBundle(VFCVT_XU_F_V)
  val vfcvt_x_f_v = CtrlBundle(VFCVT_X_F_V)
  val vfcvt_rtz_xu_f_v = CtrlBundle(VFCVT_RTZ_XU_F_V)
  val vfcvt_rtz_x_f_v = CtrlBundle(VFCVT_RTZ_X_F_V)
  val vfcvt_f_xu_v = CtrlBundle(VFCVT_F_XU_V)
  val vfcvt_f_x_v = CtrlBundle(VFCVT_F_X_V)
  val vfwcvt_xu_f_v = CtrlBundle(VFWCVT_XU_F_V)
  val vfwcvt_x_f_v = CtrlBundle(VFWCVT_X_F_V)
  val vfwcvt_rtz_xu_f_v = CtrlBundle(VFWCVT_RTZ_XU_F_V)
  val vfwcvt_rtz_x_f_v = CtrlBundle(VFWCVT_RTZ_X_F_V)
  val vfwcvt_f_xu_v = CtrlBundle(VFWCVT_F_XU_V)
  val vfwcvt_f_x_v = CtrlBundle(VFWCVT_F_X_V)
  val vfwcvt_f_f_v = CtrlBundle(VFWCVT_F_F_V)
  val vfncvt_xu_f_w = CtrlBundle(VFNCVT_XU_F_W)
  val vfncvt_x_f_w = CtrlBundle(VFNCVT_X_F_W)
  val vfncvt_rtz_xu_f_w = CtrlBundle(VFNCVT_RTZ_XU_F_W)
  val vfncvt_rtz_x_f_w = CtrlBundle(VFNCVT_RTZ_X_F_W)
  val vfncvt_f_xu_w = CtrlBundle(VFNCVT_F_XU_W)
  val vfncvt_f_x_w = CtrlBundle(VFNCVT_F_X_W)
  val vfncvt_f_f_w = CtrlBundle(VFNCVT_F_F_W)
  val vfncvt_rod_f_f_w = CtrlBundle(VFNCVT_ROD_F_F_W)

  // Vector reduction instructions
  val vredsum_vs = CtrlBundle(VREDSUM_VS)
  val vredmax_vs = CtrlBundle(VREDMAX_VS)
  val vredmaxu_vs = CtrlBundle(VREDMAXU_VS)
  val vredmin_vs = CtrlBundle(VREDMIN_VS)
  val vredminu_vs = CtrlBundle(VREDMINU_VS)
  val vredand_vs = CtrlBundle(VREDAND_VS)
  val vredor_vs = CtrlBundle(VREDOR_VS)
  val vredxor_vs = CtrlBundle(VREDXOR_VS)
  val vwredsumu_vs = CtrlBundle(VWREDSUMU_VS)
  val vwredsum_vs = CtrlBundle(VWREDSUM_VS)
  val vfredosum_vs = CtrlBundle(VFREDOSUM_VS)
  val vfredusum_vs = CtrlBundle(VFREDUSUM_VS)
  val vfredmin_vs = CtrlBundle(VFREDMIN_VS)
  val vfredmax_vs = CtrlBundle(VFREDMAX_VS)
  val vfwredusum_vs = CtrlBundle(VFWREDUSUM_VS)
  val vfwredosum_vs = CtrlBundle(VFWREDOSUM_VS)

  // Vector mask instructions
  val vmand_mm = CtrlBundle(VMAND_MM)
  val vmnand_mm = CtrlBundle(VMNAND_MM)
  val vmandn_mm = CtrlBundle(VMANDN_MM)
  val vmxor_mm = CtrlBundle(VMXOR_MM)
  val vmor_mm = CtrlBundle(VMOR_MM)
  val vmnor_mm = CtrlBundle(VMNOR_MM)
  val vmorn_mm = CtrlBundle(VMORN_MM)
  val vmxnor_mm = CtrlBundle(VMXNOR_MM)
  val vcpop_m = CtrlBundle(VCPOP_M)
  val vfirst_m = CtrlBundle(VFIRST_M)
  val vmsbf_m = CtrlBundle(VMSBF_M)
  val vmsif_m = CtrlBundle(VMSIF_M)
  val vmsof_m = CtrlBundle(VMSOF_M)
  val viota_m = CtrlBundle(VIOTA_M)
  val vid_v = CtrlBundle(VID_V)

  // Vector Permutation instructions
  val vmv_x_s = CtrlBundle(VMV_X_S)
  val vmv_s_x = CtrlBundle(VMV_S_X)
  val vfmv_f_s = CtrlBundle(VFMV_F_S)
  val vfmv_s_f = CtrlBundle(VFMV_S_F)
  val vslideup_vx = CtrlBundle(VSLIDEUP_VX)
  val vslideup_vi = CtrlBundle(VSLIDEUP_VI)
  val vslidedown_vx = CtrlBundle(VSLIDEDOWN_VX)
  val vslidedown_vi = CtrlBundle(VSLIDEDOWN_VI)
  val vslide1up_vx = CtrlBundle(VSLIDE1UP_VX)
  val vfslide1up_vf = CtrlBundle(VFSLIDE1UP_VF)
  val vslide1down_vx = CtrlBundle(VSLIDE1DOWN_VX)
  val vfslide1down_vf = CtrlBundle(VFSLIDE1DOWN_VF)
  val vrgather_vv = CtrlBundle(VRGATHER_VV)
  val vrgatherei16_vv = CtrlBundle(VRGATHEREI16_VV)
  val vrgather_vx = CtrlBundle(VRGATHER_VX)
  val vrgather_vi = CtrlBundle(VRGATHER_VI)
  val vcompress_vm = CtrlBundle(VCOMPRESS_VM)
  val vmv1r_v = CtrlBundle(VMV1R_V)
  val vmv2r_v = CtrlBundle(VMV2R_V)
  val vmv4r_v = CtrlBundle(VMV4R_V)
  val vmv8r_v = CtrlBundle(VMV8R_V)

  val vdivu_vv = CtrlBundle(VDIVU_VV)
  val vdivu_vx = CtrlBundle(VDIVU_VX)
  val vdiv_vv = CtrlBundle(VDIV_VV)
  val vdiv_vx = CtrlBundle(VDIV_VX)
  val vremu_vv = CtrlBundle(VREMU_VV)
  val vremu_vx = CtrlBundle(VREMU_VX)
  val vrem_vv = CtrlBundle(VREM_VV)
  val vrem_vx = CtrlBundle(VREM_VX)
}

