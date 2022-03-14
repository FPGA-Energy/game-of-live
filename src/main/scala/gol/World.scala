package gol

import chisel3._

class World(start: Array[Array[Int]]) extends Module {
  val io = IO(new Bundle{
    val out = Output(Vec(start.length, Vec(start.length, Bool())))
  })

  // val world = Array.tabulate(n, n)((x, y) => Cell(x, y))
  val n = start.length
  val world = Array.ofDim[Cell](n, n)

  for (y <- 0 until n) {
    for (x <- 0 until n) {
      val cell = Module(new Cell(start(y)(x) == 1))
      world(y)(x) = cell
      io.out(y)(x) := cell.io.out
    }
  }
  // neighbour numbering:
  // 7  0  1
  // 6     2
  // 5  4  3
  for (y <- 0 until n) {
    for (x <- 0 until n) {
      val north = y == 0
      val south = y == n-1
      val west = x == 0
      val east = x == n-1
      // println(s"x: $x y: $y $north $south $west $east")
      val in = world(y)(x).io.in
      in(0) := (if (north) false.B else world(y-1)(x).io.out)
      in(1) := (if (north || east) false.B else world(y-1)(x+1).io.out)
      in(2) := (if (east) false.B else world(y)(x+1).io.out)
      in(3) := (if (east || south) false.B else world(y+1)(x+1).io.out)
      in(4) := (if (south) false.B else world(y+1)(x).io.out)
      in(5) := (if (south || west) false.B else world(y+1)(x-1).io.out)
      in(6) := (if (west) false.B else world(y)(x-1).io.out)
      in(7) := (if (north || west) false.B else world(y-1)(x-1).io.out)
    }
  }
}

//object World extends App {
//  emitVerilog(new World(4))
//}
