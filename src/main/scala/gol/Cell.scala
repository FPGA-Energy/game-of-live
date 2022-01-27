package gol

import chisel3._

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
  val cnt = WireDefault(0.U(4.W))
  // what is wrong with this? Ask Chisel group
  // val vals = WireDefault(Vec(8, 0.U(4.W)))
  val vals = Wire(Vec(8, UInt(4.W)))
  for (i <- 0 to 7)  vals(i) := 0.U(4.W)
  // this is not functional
  for(i <- 0 to 7) {
    vals(i) := io.in(i).asUInt
  }
  printf("%d %d %d\n", io.in(0), io.in(1), io.in(2))
  cnt := vals.reduce(_ + _)
  printf("%d\n", cnt)


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
