package gol

import chisel3._
import chisel3.util.PopCount

/**
 * A simple GoL cell
 */
class Cell(init: Boolean) extends Module {
  val io = IO(new Bundle() {
    val in = Input(Vec(8, Bool()))
    val out = Output(UInt(1.W))
  })

  val regCell = RegInit(init.B)
  val cnt = PopCount(io.in)

  when(regCell) {
    when(cnt === 2.U || cnt === 3.U) {
      regCell := true.B
    }
  }.elsewhen(cnt === 3.U) {
    regCell := true.B
  }.otherwise {
    regCell := false.B
  }

  io.out := regCell
}

object Cell extends App {
  println(getVerilogString(new Cell(true)))

  def apply(x: Int, y: Int) {
    Module(new Cell(true))
  }
}
