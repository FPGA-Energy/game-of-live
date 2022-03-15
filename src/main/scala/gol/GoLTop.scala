package gol

import chisel3._

class GoLTop(n: Int) extends Module {
  val io = IO(new Bundle{
    val out = Output(Bool())
  })

  val start = Util.getRandomWorld(n)
  val gol = Module(new World(start))

  io.out := RegNext(RegNext(RegNext(RegNext(gol.io.out(n/2)(n/2)))))
}

object GoLTop extends App {
  emitVerilog(new GoLTop(40), Array("--target-dir", "generated"))
}
