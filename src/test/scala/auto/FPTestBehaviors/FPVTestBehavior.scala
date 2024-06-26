package darecreek.vfuAutotest.alu

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3._
import chiseltest.WriteVcdAnnotation
import scala.reflect.io.File
import scala.reflect.runtime.universe._
import scala.util.control.Breaks._

import darecreek.exu.vfu._
import darecreek.exu.vfu.alu._
import darecreek.exu.vfu.fp._
import darecreek.exu.vfu.VInstructions._

// VN ============================================
class VfncvtxufwTestBehavior extends VfTestBehavior("vfncvt.xu.f.w.data", ctrlBundles.vfncvt_xu_f_w, "u", "vfncvt_xu_f_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x10), ooo=true) {}
class VfncvtxfwTestBehavior extends VfTestBehavior("vfncvt.x.f.w.data", ctrlBundles.vfncvt_x_f_w, "u", "vfncvt_x_f_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x11), ooo=true) {}
class VfncvtfxuwTestBehavior extends VfTestBehavior("vfncvt.f.xu.w.data", ctrlBundles.vfncvt_f_xu_w, "u", "vfncvt_f_xu_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x12), ooo=true) {}
class VfncvtfxwTestBehavior extends VfTestBehavior("vfncvt.f.x.w.data", ctrlBundles.vfncvt_f_x_w, "u", "vfncvt_f_x_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x13), ooo=true) {}
class VfncvtffwTestBehavior extends VfTestBehavior("vfncvt.f.f.w.data", ctrlBundles.vfncvt_f_f_w, "u", "vfncvt_f_f_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x14), ooo=true) {}
class VfncvtrodffwTestBehavior extends VfTestBehavior("vfncvt.rod.f.f.w.data", ctrlBundles.vfncvt_rod_f_f_w, "u", "vfncvt_rod_f_f_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x15), ooo=true) {}
class VfncvtrtzxufwTestBehavior extends VfTestBehavior("vfncvt.rtz.xu.f.w.data", ctrlBundles.vfncvt_rtz_xu_f_w, "u", "vfncvt_rtz_xu_f_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x16), ooo=true) {}
class VfncvtrtzxfwTestBehavior extends VfTestBehavior("vfncvt.rtz.x.f.w.data", ctrlBundles.vfncvt_rtz_x_f_w, "u", "vfncvt_rtz_x_f_w", () => new NarrowFPResult(), vn=true, vs1encoding=Some(0x17), ooo=true) {}

// Normal ============================================
// vv ================================================================================================================
class VfaddvvTestBehavior extends VfTestBehavior("vfadd.vv.data", ctrlBundles.vfadd_vv, "-", "vfadd_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfsubvvTestBehavior extends VfTestBehavior("vfsub.vv.data", ctrlBundles.vfsub_vv, "-", "vfsub_vv", () => new NormalFPResult(), normal=true, ooo=true) {}

class VfmulvvTestBehavior extends VfTestBehavior("vfmul.vv.data", ctrlBundles.vfmul_vv, "-", "vfmul_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmaccvvTestBehavior extends VfTestBehavior("vfmacc.vv.data", ctrlBundles.vfmacc_vv, "-", "vfmacc_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmaccvvTestBehavior extends VfTestBehavior("vfnmacc.vv.data", ctrlBundles.vfnmacc_vv, "-", "vfnmacc_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmsacvvTestBehavior extends VfTestBehavior("vfmsac.vv.data", ctrlBundles.vfmsac_vv, "-", "vfmsac_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmsacvvTestBehavior extends VfTestBehavior("vfnmsac.vv.data", ctrlBundles.vfnmsac_vv, "-", "vfnmsac_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmaddvvTestBehavior extends VfTestBehavior("vfmadd.vv.data", ctrlBundles.vfmadd_vv, "-", "vfmadd_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmaddvvTestBehavior extends VfTestBehavior("vfnmadd.vv.data", ctrlBundles.vfnmadd_vv, "-", "vfnmadd_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmsubvvTestBehavior extends VfTestBehavior("vfmsub.vv.data", ctrlBundles.vfmsub_vv, "-", "vfmsub_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmsubvvTestBehavior extends VfTestBehavior("vfnmsub.vv.data", ctrlBundles.vfnmsub_vv, "-", "vfnmsub_vv", () => new NormalFPResult(), normal=true, ooo=true) {}

class VfminvvTestBehavior extends VfTestBehavior("vfmin.vv.data", ctrlBundles.vfmin_vv, "-", "vfmin_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmaxvvTestBehavior extends VfTestBehavior("vfmax.vv.data", ctrlBundles.vfmax_vv, "-", "vfmax_vv", () => new NormalFPResult(), normal=true, ooo=true) {}

class VfsgnjvvTestBehavior extends VfTestBehavior("vfsgnj.vv.data", ctrlBundles.vfsgnj_vv, "s", "vfsgnj_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfsgnjnvvTestBehavior extends VfTestBehavior("vfsgnjn.vv.data", ctrlBundles.vfsgnjn_vv, "s", "vfsgnjn_vv", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfsgnjxvvTestBehavior extends VfTestBehavior("vfsgnjx.vv.data", ctrlBundles.vfsgnjx_vv, "s", "vfsgnjx_vv", () => new NormalFPResult(), normal=true, ooo=true) {}

// vf ================================================================================================================
class VfaddvfTestBehavior extends VfTestBehavior("vfadd.vf.data", ctrlBundles.vfadd_vf, "-", "vfadd_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfsubvfTestBehavior extends VfTestBehavior("vfsub.vf.data", ctrlBundles.vfsub_vf, "-", "vfsub_vf", () => new NormalFPResult(), normal=true, ooo=true) {}

class VfmulvfTestBehavior extends VfTestBehavior("vfmul.vf.data", ctrlBundles.vfmul_vf, "-", "vfmul_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmaccvfTestBehavior extends VfTestBehavior("vfmacc.vf.data", ctrlBundles.vfmacc_vf, "-", "vfmacc_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmaccvfTestBehavior extends VfTestBehavior("vfnmacc.vf.data", ctrlBundles.vfnmacc_vf, "-", "vfnmacc_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmsacvfTestBehavior extends VfTestBehavior("vfmsac.vf.data", ctrlBundles.vfmsac_vf, "-", "vfmsac_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmsacvfTestBehavior extends VfTestBehavior("vfnmsac.vf.data", ctrlBundles.vfnmsac_vf, "-", "vfnmsac_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmaddvfTestBehavior extends VfTestBehavior("vfmadd.vf.data", ctrlBundles.vfmadd_vf, "-", "vfmadd_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmaddvfTestBehavior extends VfTestBehavior("vfnmadd.vf.data", ctrlBundles.vfnmadd_vf, "-", "vfnmadd_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmsubvfTestBehavior extends VfTestBehavior("vfmsub.vf.data", ctrlBundles.vfmsub_vf, "-", "vfmsub_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfnmsubvfTestBehavior extends VfTestBehavior("vfnmsub.vf.data", ctrlBundles.vfnmsub_vf, "-", "vfnmsub_vf", () => new NormalFPResult(), normal=true, ooo=true) {}

class VfminvfTestBehavior extends VfTestBehavior("vfmin.vf.data", ctrlBundles.vfmin_vf, "-", "vfmin_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmaxvfTestBehavior extends VfTestBehavior("vfmax.vf.data", ctrlBundles.vfmax_vf, "-", "vfmax_vf", () => new NormalFPResult(), normal=true, ooo=true) {}

class VfsgnjvfTestBehavior extends VfTestBehavior("vfsgnj.vf.data", ctrlBundles.vfsgnj_vf, "s", "vfsgnj_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfsgnjnvfTestBehavior extends VfTestBehavior("vfsgnjn.vf.data", ctrlBundles.vfsgnjn_vf, "s", "vfsgnjn_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfsgnjxvfTestBehavior extends VfTestBehavior("vfsgnjx.vf.data", ctrlBundles.vfsgnjx_vf, "s", "vfsgnjx_vf", () => new NormalFPResult(), normal=true, ooo=true) {}

// others ================================================================================================================
class VfrsubvfTestBehavior extends VfTestBehavior("vfrsub.vf.data", ctrlBundles.vfrsub_vf, "-", "vfrsub_vf", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmergevfmTestBehavior extends VfTestBehavior("vfmerge.vfm.data", ctrlBundles.vfmerge_vfm, "-", "vfmerge_vfm", () => new NormalFPResult(), normal=true, ooo=true) {}
class VfmvTestBehavior extends VfTestBehavior("vfmv.v.f.data", ctrlBundles.vfmv_v_f, "-", "vfmv_v_f", () => new NormalFPResult(), normal=true, ooo=true) {}

class Vfrsqrt7vTestBehavior extends VfTestBehavior("vfrsqrt7.v.data", ctrlBundles.vfrsqrt7_v, "-", "vfrsqrt7_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x04), ooo=true) {}
class Vfrec7vTestBehavior extends VfTestBehavior("vfrec7.v.data", ctrlBundles.vfrec7_v, "-", "vfrec7_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x05), ooo=true) {}
class VfclassvTestBehavior extends VfTestBehavior("vfclass.v.data", ctrlBundles.vfclass_v, "-", "vfclass_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x10), ooo=true) {}


class VfcvtxufvTestBehavior extends VfTestBehavior("vfcvt.xu.f.v.data", ctrlBundles.vfcvt_xu_f_v, "-", "vfcvt_xu_f_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x00), ooo=true) {}
class VfcvtxfvTestBehavior extends VfTestBehavior("vfcvt.x.f.v.data", ctrlBundles.vfcvt_x_f_v, "-", "vfcvt_x_f_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x01), ooo=true) {}
class VfcvtfxuvTestBehavior extends VfTestBehavior("vfcvt.f.xu.v.data", ctrlBundles.vfcvt_f_xu_v, "-", "vfcvt_f_xu_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x02), ooo=true) {}
class VfcvtfxvTestBehavior extends VfTestBehavior("vfcvt.f.x.v.data", ctrlBundles.vfcvt_f_x_v, "-", "vfcvt_f_x_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x03), ooo=true) {}
class VfcvtrtzxufvTestBehavior extends VfTestBehavior("vfcvt.rtz.xu.f.v.data", ctrlBundles.vfcvt_rtz_xu_f_v, "-", "vfcvt_rtz_xu_f_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x06), ooo=true) {}
class VfcvtrtzxfvTestBehavior extends VfTestBehavior("vfcvt.rtz.x.f.v.data", ctrlBundles.vfcvt_rtz_x_f_v, "-", "vfcvt_rtz_x_f_v", () => new NormalFPResult(), normal=true, vs1encoding=Some(0x07), ooo=true) {}

// vw ====================================================================
class VfwaddwvTestBehavior extends VfTestBehavior("vfwadd.wv.data", ctrlBundles.vfwadd_wv, "s", "vfwadd_wv", () => new NormalFPResult(), vw=true, vfwvv=false, ooo=true) {}
class VfwsubwvTestBehavior extends VfTestBehavior("vfwsub.wv.data", ctrlBundles.vfwsub_wv, "s", "vfwsub_wv", () => new NormalFPResult(), vw=true, vfwvv=false, ooo=true) {}
class VfwaddwfTestBehavior extends VfTestBehavior("vfwadd.wf.data", ctrlBundles.vfwadd_wf, "s", "vfwadd_wf", () => new NormalFPResult(), vw=true, vfwvv=false, ooo=true) {}
class VfwsubwfTestBehavior extends VfTestBehavior("vfwsub.wf.data", ctrlBundles.vfwsub_wf, "s", "vfwsub_wf", () => new NormalFPResult(), vw=true, vfwvv=false, ooo=true) {}

class VfwaddvvTestBehavior extends VfTestBehavior("vfwadd.vv.data", ctrlBundles.vfwadd_vv, "s", "vfwadd_vv", () => new NormalFPResult(), vw=true, vfwvv=true, ooo=true) {}
class VfwsubvvTestBehavior extends VfTestBehavior("vfwsub.vv.data", ctrlBundles.vfwsub_vv, "s", "vfwsub_vv", () => new NormalFPResult(), vw=true, vfwvv=true, ooo=true) {}

class VfwaddvfTestBehavior extends VfTestBehavior("vfwadd.vf.data", ctrlBundles.vfwadd_vf, "s", "vfwadd_vf", () => new NormalFPResult(), vw=true, vfwvv=true, ooo=true) {}
class VfwsubvfTestBehavior extends VfTestBehavior("vfwsub.vf.data", ctrlBundles.vfwsub_vf, "s", "vfwsub_vf", () => new NormalFPResult(), vw=true, vfwvv=true, ooo=true) {}

class VfwmaccvvTestBehavior extends VfTestBehavior("vfwmacc.vv.data", ctrlBundles.vfwmacc_vv, "s", "vfwmacc_vv", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwnmaccvvTestBehavior extends VfTestBehavior("vfwnmacc.vv.data", ctrlBundles.vfwnmacc_vv, "s", "vfwnmacc_vv", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwmsacvvTestBehavior extends VfTestBehavior("vfwmsac.vv.data", ctrlBundles.vfwmsac_vv, "s", "vfwmsac_vv", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwnmsacvvTestBehavior extends VfTestBehavior("vfwnmsac.vv.data", ctrlBundles.vfwnmsac_vv, "s", "vfwnmsac_vv", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwmulvvTestBehavior extends VfTestBehavior("vfwmul.vv.data", ctrlBundles.vfwmul_vv, "s", "vfwmul_vv", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwmaccvfTestBehavior extends VfTestBehavior("vfwmacc.vf.data", ctrlBundles.vfwmacc_vf, "s", "vfwmacc_vf", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwnmaccvfTestBehavior extends VfTestBehavior("vfwnmacc.vf.data", ctrlBundles.vfwnmacc_vf, "s", "vfwnmacc_vf", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwmsacvfTestBehavior extends VfTestBehavior("vfwmsac.vf.data", ctrlBundles.vfwmsac_vf, "s", "vfwmsac_vf", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwnmsacvfTestBehavior extends VfTestBehavior("vfwnmsac.vf.data", ctrlBundles.vfwnmsac_vf, "s", "vfwnmsac_vf", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}
class VfwmulvfTestBehavior extends VfTestBehavior("vfwmul.vf.data", ctrlBundles.vfwmul_vf, "s", "vfwmul_vf", () => new NormalFPResult(), vw=true, vfwmul_like=true, vfwvv=true, ooo=true) {}

class VfwcvtxufvTestBehavior extends VfTestBehavior("vfwcvt.xu.f.v.data", ctrlBundles.vfwcvt_xu_f_v, "s", "vfwcvt_xu_f_v", () => new NormalFPResult(), vw=true, vs1encoding=Some(0x08), vfwvv=true, ooo=true) {}
class VfwcvtxfvTestBehavior extends VfTestBehavior("vfwcvt.x.f.v.data", ctrlBundles.vfwcvt_x_f_v, "s", "vfwcvt_x_f_v", () => new NormalFPResult(), vw=true, vs1encoding=Some(0x09), vfwvv=true, ooo=true) {}
class VfwcvtfxuvTestBehavior extends VfTestBehavior("vfwcvt.f.xu.v.data", ctrlBundles.vfwcvt_f_xu_v, "s", "vfwcvt_f_xu_v", () => new NormalFPResult(), vw=true, vs1encoding=Some(0x0A), vfwvv=true, ooo=true) {}
class VfwcvtfxvTestBehavior extends VfTestBehavior("vfwcvt.f.x.v.data", ctrlBundles.vfwcvt_f_x_v, "s", "vfwcvt_f_x_v", () => new NormalFPResult(), vw=true, vs1encoding=Some(0x0B), vfwvv=true, ooo=true) {}
class VfwcvtffvTestBehavior extends VfTestBehavior("vfwcvt.f.f.v.data", ctrlBundles.vfwcvt_f_f_v, "s", "vfwcvt_f_f_v", () => new NormalFPResult(), vw=true, vs1encoding=Some(0x0C), vfwvv=true, ooo=true) {}
class VfwcvtrtzxufvTestBehavior extends VfTestBehavior("vfwcvt.rtz.xu.f.v.data", ctrlBundles.vfwcvt_rtz_xu_f_v, "s", "vfwcvt_rtz_xu_f_v", () => new NormalFPResult(), vw=true, vs1encoding=Some(0x0E), vfwvv=true, ooo=true) {}
class VfwcvtrtzxfvTestBehavior extends VfTestBehavior("vfwcvt.rtz.x.f.v.data", ctrlBundles.vfwcvt_rtz_x_f_v, "s", "vfwcvt_rtz_x_f_v", () => new NormalFPResult(), vw=true, vs1encoding=Some(0x0F), vfwvv=true, ooo=true) {}

// ==============================================================
// In-order accepting uops instructions
// ==============================================================

class VfredosumvsTestBehavior extends VfTestBehavior("vfredosum.vs.data", ctrlBundles.vfredosum_vs, "u", "vfredosum_vs", () => new RedFPResult(false), vred=true) {}
class VfredusumvsTestBehavior extends VfTestBehavior("vfredusum.vs.data", ctrlBundles.vfredusum_vs, "u", "vfredusum_vs", () => new RedFPResult(false), vred=true) {}
// class VfredusumvsTestBehavior extends VfTestBehavior("vfredusum.vs.data", ctrlBundles.vfredusum_vs, "u", "vfredusum_vs", () => new RedUSumVsFPResult(false), vred=true, disable_fflags=true) {}
class VfredmaxvsTestBehavior extends VfTestBehavior("vfredmax.vs.data", ctrlBundles.vfredmax_vs, "u", "vfredmax_vs", () => new RedFPResult(false), vred=true) {}
class VfredminvsTestBehavior extends VfTestBehavior("vfredmin.vs.data", ctrlBundles.vfredmin_vs, "u", "vfredmin_vs", () => new RedFPResult(false), vred=true) {}

class VfwredosumvsTestBehavior extends VfTestBehavior("vfwredosum.vs.data", ctrlBundles.vfwredosum_vs, "u", "vfwredosum_vs", () => new RedFPResult(true), vred=true, vwred=true) {}
class VfwredusumvsTestBehavior extends VfTestBehavior("vfwredusum.vs.data", ctrlBundles.vfwredusum_vs, "u", "vfwredusum_vs", () => new RedFPResult(true), vred=true, vwred=true) {}
// class VfwredusumvsTestBehavior extends VfTestBehavior("vfwredusum.vs.data", ctrlBundles.vfwredusum_vs, "u", "vfwredusum_vs", () => new RedUSumVsFPResult(true), vred=true, vwred=true, disable_fflags=true) {}

class VmfeqvvTestBehavior extends VfTestBehavior("vmfeq.vv.data", ctrlBundles.vmfeq_vv, "u", "vmfeq_vv", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmfnevvTestBehavior extends VfTestBehavior("vmfne.vv.data", ctrlBundles.vmfne_vv, "u", "vmfne_vv", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmfltvvTestBehavior extends VfTestBehavior("vmflt.vv.data", ctrlBundles.vmflt_vv, "s", "vmflt_vv", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmflevvTestBehavior extends VfTestBehavior("vmfle.vv.data", ctrlBundles.vmfle_vv, "s", "vmfle_vv", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmfeqvfTestBehavior extends VfTestBehavior("vmfeq.vf.data", ctrlBundles.vmfeq_vf, "u", "vmfeq_vf", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmfnevfTestBehavior extends VfTestBehavior("vmfne.vf.data", ctrlBundles.vmfne_vf, "u", "vmfne_vf", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmfltvfTestBehavior extends VfTestBehavior("vmflt.vf.data", ctrlBundles.vmflt_vf, "s", "vmflt_vf", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmflevfTestBehavior extends VfTestBehavior("vmfle.vf.data", ctrlBundles.vmfle_vf, "s", "vmfle_vf", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmfgtvfTestBehavior extends VfTestBehavior("vmfgt.vf.data", ctrlBundles.vmfgt_vf, "-", "vmfgt_vf", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}
class VmfgevfTestBehavior extends VfTestBehavior("vmfge.vf.data", ctrlBundles.vmfge_vf, "-", "vmfge_vf", () => new NarrowToOneFPResult(), normal=true, narrow_to_1=true) {}

class VfTestBehavior(fn : String, cb : CtrlBundle, 
    s : String, instid : String, var fpRes1 : () => FPResult,
    vred : Boolean = false, vn : Boolean = false, normal : Boolean = false, 
    vw : Boolean = false, narrow_to_1 : Boolean = false,
    vfwvv : Boolean = false, vs1encoding : Option[Int] = None,
    vfwmul_like : Boolean = false, vwred : Boolean = false, disable_fflags : Boolean = false, ooo : Boolean = false) extends TestBehavior(fn, cb, s, instid) {
    
    override def getDut() : Module               = {
        val dut = new VFPUWrapper
        return dut
    }

    override def testMultiple(simi:Map[String,String],ctrl:CtrlBundle,s:String, dut:VFPUWrapper) : Unit = {
        val vf = simi.get("VS1") == None && simi.get("FS1") != None
        val v = simi.get("VS1") == None && simi.get("FS1") == None
        val vv = !v && !vf
        val hasvs2 = simi.get("VS2") != None

        // Data =========================================================================================================================================================
        var vs1data : Array[String] = Array()
        if (vv)
            vs1data = UtilFuncs.multilmuldatahandle(simi.get("VS1").get)
        if (vf) {
            vs1data = UtilFuncs.multilmuldatahandle(simi.get("FS1").get)
            // println(s"vf fs1 ${vs1data(0)}")
            vs1data(0) = s"h${vs1data(0).slice(17, 33)}"
            // println(s"after vf fs1 ${vs1data(0)}")
        }

        var vs2data : Array[String] = Array()
        if (hasvs2)
            vs2data = UtilFuncs.multilmuldatahandle(simi.get("VS2").get)
        
        val oldvddata = UtilFuncs.multilmuldatahandle(simi.get("OLD_VD").get)
        
        val hasMask = simi.get("MASK") != None
        var mask = Array("hffff_ffff_ffff_ffff_ffff_ffff_ffff_ffff")
        if (hasMask)
            mask = UtilFuncs.multilmuldatahandle(simi.get("MASK").get)
        
        // Control Signal =========================================================================================================================================================
        val vflmul = simi.get("vflmul").get
        val vsew = UtilFuncs.vsewconvert(simi.get("vsew").get)
        val vxrm = simi.get("vxrm").get.toInt
        val vstart = getVstart(simi)
        var vm = (simi.get("vm").get.toInt == 1)
        val frm = getfrm(simi)

        // Expected Output =========================================================================================================================================================
        var hasVd = simi.get("VD") != None
        var hasRd = simi.get("FD") != None
        var expectvd : Array[String] = Array()
        if (hasVd)
            expectvd = UtilFuncs.multilmuldatahandle(simi.get("VD").get)
        if (hasRd)
            expectvd = UtilFuncs.multilmuldatahandle(simi.get("FD").get)
        val expectfflags = getFflags(simi)

        // ================================================================================================================

        var n_inputs = 1
        if(vflmul == "2.000000") n_inputs = 2
        if(vflmul == "4.000000") n_inputs = 4
        if(vflmul == "8.000000") n_inputs = 8
        
        var n_ops = n_inputs
        var n_res = n_inputs
        if (vred || narrow_to_1 || hasRd) n_res = 1
        if (vw || vn) {
            if(vflmul == "1.000000" ||
                vflmul == "2.000000" ||
                vflmul == "4.000000" ||
                vflmul == "8.000000") 
                n_ops = n_inputs * 2
                n_res = n_ops
        }

        // fpRes ================================================================================================================
        var fpRes : FPResult = fpRes1()
        if (hasRd) {
            fpRes = new FdFPResult()
        }
        fpRes.setup(n_res, (a, b, c) => this.dump(a, b, c, "")) // * fpRes

        // ================================================================================================================
        var vd : BigInt = 0
        var vdres = false
        val MAX_READY_WAIT = 100
        var curReadyWait = 0
        var j = 0
        var ctrlBundles : Map[Int, CtrlBundle] = Map()

        val uop_order = scala.util.Random.shuffle(List.range(0, n_ops))
        breakable{ while (j < n_ops){
            dut.io.in.valid.poke(true.B) // TODO randomly block

            // preparing input ====================================
            var uopIdx = j
            if (ooo) {
                uopIdx = uop_order(j)
            }
            
            var srcBundle = SrcBundle(
                mask=mask(0)
            )
            var ctrlBundle = ctrl.copy(
                vsew=vsew,
                narrow=vn,
                narrow_to_1=narrow_to_1,
                widen2=(vw && (!vfwvv)), //  || vwred,
                widen = (vw && vfwvv),
                vl=simi.get("vl").get.toInt,
                vlmul = UtilFuncs.lmulconvert(vflmul).toInt, 
                ma = (simi.get("ma").get.toInt == 1),
                ta = (simi.get("ta").get.toInt == 1),
                vm = vm,
                uopIdx=uopIdx,
                uopEnd = (!ooo) && (uopIdx == n_ops - 1), // 10.19
                vxrm = vxrm,
                frm=frm,
                vstart = vstart
            )

            if (vred) {
                srcBundle.vs1=vs1data(n_inputs - 1)
                srcBundle.vs2=vs2data(n_inputs - 1 - uopIdx)
                srcBundle.old_vd=oldvddata(n_inputs - 1)
            }
            else if (vn) {
                srcBundle.vs2=vs2data(n_ops - 1 - uopIdx)
                srcBundle.old_vd=oldvddata(n_inputs - 1 - (uopIdx / 2))
            }
            else if (vw) {
                srcBundle.vs2=vs2data(n_ops - 1 -uopIdx)
                srcBundle.old_vd=oldvddata(n_ops - 1 -uopIdx)
                if (vfwvv || vfwmul_like) {
                    srcBundle.vs2=vs2data(n_inputs - 1 - (uopIdx / 2))
                }

                if (vf) {
                    srcBundle.rs1 = vs1data(0)
                }
                if (vv) {
                    srcBundle.vs1 = vs1data(n_inputs - 1 - (uopIdx / 2))
                }
            }
            else if (normal) {
                var vs2 = "h0"
                if(hasvs2) vs2 = vs2data(n_inputs - 1 -uopIdx)
                srcBundle.vs2=vs2
                srcBundle.old_vd=oldvddata(n_inputs - 1 -uopIdx)
                if (vf) {
                    srcBundle.rs1 = vs1data(0)
                } 
                if (vv) {
                    srcBundle.vs1 = vs1data(n_inputs - 1 -uopIdx)
                }
            }

            // special case in spec
            vs1encoding match {
                case Some(newvs1) => {
                    ctrlBundle.vs1_imm = newvs1
                }
                case None => {}
            }

            // sending input ====================================
            // dut.io.dontCare.poke(false.B)
            
            dut.io.in.bits.poke(genVFuInput(
                srcBundle, 
                ctrlBundle
            ))
            dut.io.redirect.poke(genFSMRedirect())

            // waiting for dut's ready signal, which represents an ack of the uop ========
            while((dut.io.in.ready.peek().litValue != 1) &&
                    curReadyWait < MAX_READY_WAIT) {
                
                // before ticking clock, check if any vd comes out from dut
                if (!fpRes.finished()) { // * fpRes
                    fpRes.checkAndCompare(dut, simi, ctrlBundles, expectvd)
                }
                dut.clock.step(1)
                curReadyWait += 1
            }

            // waits too long.. =====================================
            if (!(curReadyWait < MAX_READY_WAIT)) {
                println(s"no io.ready signal received")
                dump(simi, s"(no io.ready signal received), sent ${j} uops", "(no io.ready signal received)")
            }
            assert(curReadyWait < MAX_READY_WAIT)
            curReadyWait = 0

            // add ctrlBundle to the uopIdx -> ctrlBundle map ========
            ctrlBundles = ctrlBundles + (uopIdx -> ctrlBundle)
            println(s"uop $uopIdx has been sent")

            // before ticking clock, check if any vd comes out from dut
            if (!fpRes.finished()) { // * fpRes
                fpRes.checkAndCompare(dut, simi, ctrlBundles, expectvd)
            }
            dut.clock.step(1)
            j += 1
        } }
        dut.io.in.valid.poke(false.B)
        dut.io.in.bits.uop.uopEnd.poke(false.B)

        // checking for rest output vds ==============================================================================
        val LOOP_MAX = 100
        var curIter = 0
        var fflags : Int = 0
        breakable{ while(true) {
            
            if (!(curIter < LOOP_MAX)) {
                println("no vd received after LOOP_MAX")
                dump(simi, s"(no vd received after LOOP_MAX), received ${fpRes.cur_res}", "(no vd received after LOOP_MAX)")
            }
            assert(curIter < LOOP_MAX)

            if (fpRes.finished()) { // * fpRes
                break
            }

            var srcBundle = SrcBundle(mask="h0")
            var ctrlBundle = ctrl.copy()

            // dut.io.dontCare.poke(true.B)
            dut.io.in.bits.poke(genVFuInput(
                srcBundle, 
                ctrlBundle
            ))
            dut.io.redirect.poke(genFSMRedirect())

            fpRes.checkAndCompare(dut, simi, ctrlBundles, expectvd) // * fpRes

            dut.clock.step(1)

            curIter += 1
        } }

        fflags = fpRes.getFflags() // * fpRes
        var fflagsRes = fflags == expectfflags

        if (!disable_fflags && !fflagsRes) {
            println("fflags incorrect")
            dump(simi, f"(fflags) h$fflags%016x", f"(fflags) h$expectfflags%016x")
        }

        if (!disable_fflags) {
            assert(fflagsRes)
        }
    }

    override def testSingle(simi:Map[String,String],ctrl:CtrlBundle,s:String, dut:VFPUWrapper) : Unit = {
        testMultiple(simi, ctrl, s, dut)
    }
}