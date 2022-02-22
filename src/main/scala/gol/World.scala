package gol

import chisel3._

class World(n: Int) extends Module {
  val io = IO(new Bundle{
    val start = Input(Bool())
  })

  // val world = Array.tabulate(n, n)((x, y) => Cell(x, y))
  val world = Array.ofDim[Cell](n, n)

  for (x <- 0 until n) {
    for (y <- 0 until n) {
      world(x)(y) = Module(new Cell(true))
    }
  }
  for (x <- 0 until n) {
    for (y <- 0 until n) {
      val north = y == 0
      val south = y == n-1
      val west = x == 0
      val east = x == n-1
      world(x)(y).io.in := io.start

    }
  }
}

object World extends App {
  emitVerilog(new World(4))
}
