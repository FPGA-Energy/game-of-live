package gol

import chisel3._
import chisel3.util._
import chisel3.experimental._
import scala.math.abs
object Xilinx {

  def roundAt(p: Int)(n: Double): Double = { val s = math pow (10, p); (math round n * s) / s }

  implicit class BundleExpander[T <: Bundle](b: T) {
    def expand(assignments: T => Any*): T = {
      assignments.foreach(f => f(b))
      b
    }
  }

  object IBUF {
    def apply(clk: Clock): Clock = {
      val ibuf = Module(new IBUF)
      ibuf.io.I := clk
      ibuf.io.O
    }
  }
  class IBUF extends BlackBox {
    val io = IO(new Bundle {
      val O = Output(Clock())
      val I = Input(Clock())
    })
  }

  object BUFG {
    def apply(clk: Clock): Clock = {
      val ibuf = Module(new BUFG)
      ibuf.io.I := clk
      ibuf.io.O
    }
  }

  class BUFG extends BlackBox {
    val io = IO(new Bundle {
      val O = Output(Clock())
      val I = Input(Clock())
    })
  }

  object MMCME2_ADV {
    def apply(clk: Clock, reset: Reset, inPeriod: Double, mult: Double, div: Double): Clock = {
      val mmcme = Module(new MMCME2_ADV(inPeriod, mult, div))

      println(s"MMCME($inPeriod, $mult, $div)")
      mmcme.io.expand(
        _.CLKFBIN := BUFG(mmcme.io.CLKFBOUT),
        _.CLKINSEL := 1.B,
        _.CLKIN1 := IBUF(clk),
        _.CLKIN2 := 0.B.asClock,
        _.DADDR := 0.U,
        _.DCLK := 0.B.asClock,
        _.DEN := 0.B,
        _.DI := 0.U,
        _.DWE := 0.B,
        _.PSCLK := 0.B.asClock,
        _.PSEN := 0.B,
        _.PSINCDEC := 0.B,
        _.PWRDWN := 0.B,
        _.RST := reset
      )

      BUFG(mmcme.io.CLKOUT0)
    }
    def apply(clk: Clock, reset: Reset, clocks: (Double, Double)): Clock = {
      var closest = (Double.MaxValue,0.0,0.0)
      for (mult <- 2.0 to 64.0 by 0.1) {
        for (div <- 1.0 to 128.0 by 0.1) {
          if (clocks._1 * mult >= 600.0) {
            val outClock = (clocks._1 * mult) / div
            if (outClock == clocks._2) {
              return apply(clk, reset, roundAt(3)(1000.0/clocks._1), mult, div)
            } else {
              val dif = outClock - clocks._2
              if (abs(dif) < closest._1) {
                closest = (abs(dif), roundAt(3)(mult), roundAt(3)(div))
              }
            }
          }
        }
      }
      println(s"dif: ${closest._1}")
      apply(clk, reset, roundAt(3)(1.0/clocks._1), closest._2, closest._3)
    }
  }


  class MMCME2_ADV(
                    inPeriod: Double,
                    mult: Double,
                    div: Double
                  ) extends BlackBox(Map(
    "CLKIN1_PERIOD" -> inPeriod, // 0.000 to 100.000
    "CLKFBOUT_MULT_F" -> mult, // 2.000 to 64.000
    "CLKOUT0_DIVIDE_F" -> div // 1.000 to 128.000
  )) {
    val io = IO(new Bundle {
      val CLKFBIN = Input(Clock())
      val CLKFBOUT = Output(Clock())
      val CLKFBOUTB = Output(Clock())
      val CLKFBSTOPPED = Output(Bool())
      val CLKINSEL = Input(Bool())
      val CLKINSTOPPED = Output(Bool())
      val CLKIN1 = Input(Clock())
      val CLKIN2 = Input(Clock())
      val CLKOUT0 = Output(Clock())
      val CLKOUT0B = Output(Clock())
      val CLKOUT1 = Output(Clock())
      val CLKOUT1B = Output(Clock())
      val CLKOUT2 = Output(Clock())
      val CLKOUT2B = Output(Clock())
      val CLKOUT3 = Output(Clock())
      val CLKOUT3B = Output(Clock())
      val CLKOUT4 = Output(Clock())
      val CLKOUT5 = Output(Clock())
      val CLKOUT6 = Output(Clock())
      val DADDR = Input(UInt(7.W))
      val DCLK = Input(Clock())
      val DEN = Input(Bool())
      val DI = Input(UInt(16.W))
      val DO = Output(UInt(16.W))
      val DRDY = Output(Bool())
      val DWE = Input(Bool())
      val LOCKED = Output(Bool())
      val PSCLK = Input(Clock())
      val PSDONE = Output(Bool())
      val PSEN = Input(Bool())
      val PSINCDEC = Input(Bool())
      val PWRDWN = Input(Bool())
      val RST = Input(Reset())
    })

  }

}
