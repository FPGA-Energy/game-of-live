package gol

import chisel3._
import chisel3.util.PopCount

/**
 * A simple GoL cell
 */
class Cell extends Module {
  val io = IO(new Bundle() {
    val in = Input(Vec(8, Bool()))
    val load = Input(Bool())
    val loadVal = Input(UInt(1.W))
    val out = Output(UInt(1.W))
  })

  val regCell = RegInit(0.U)
  val cnt = PopCount(io.in)


//  when (io.load) {
//    regCell := io.loadVal
//  } otherwise {
    when (regCell === 1.U) {
      when (cnt === 2.U || cnt === 3.U) {
        regCell := 1.U
      }
    } .elsewhen (cnt === 3.U) {
      regCell := 1.U
    } .otherwise {
      regCell := 0.U
    }

    io.out := regCell
//  }
}
