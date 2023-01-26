package gol

import chisel3._
import gol.Xilinx.MMCME2_ADV

class GoLTop(file: String) extends Module {
  val io = IO(new Bundle{
    val out = Output(Bool())
  })

  val (n, worldInit) = Util.worldFromFile(file)

  val frequencyMap = Map(
    10 -> 300,
    20 -> 300,
    30 -> 300,
    40 -> 280,
    50 -> 290,
    60 -> 290,
    62 -> 290
  )

  withClock(MMCME2_ADV(clock, reset, 12.0 -> frequencyMap(n))) {


    val gol = Module(new World(worldInit))

    io.out := RegNext(RegNext(RegNext(RegNext(gol.io.out(n / 2)(n / 2)))))

  }
}

object GoLTop extends App {
  emitVerilog(new GoLTop("src/test/test-arrays/test40x40/test_beacon_6x6_array_40x40.in"), Array("--target-dir", "generated"))
}
