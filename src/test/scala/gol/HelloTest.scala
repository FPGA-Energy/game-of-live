/*
 * Test a single cell.
 *
 * Author: Martin Schoeberl (martin@jopdesign.com)
 *
 */

package gol

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class HelloTest extends AnyFlatSpec with ChiselScalatestTester {

  "GoL" should "play a simple pattern" in {
    val start = Array(Array(0, 0, 0), Array(1, 1, 1), Array(0, 0, 0))
    test(new World(start)) { dut =>
      val n = start.length

      def printWorld(): Unit = {
        def field(y: Int, x: Int) = if (dut.io.out(y)(x).peek().litToBoolean) 1 else 0

        for (y <- 0 until n) {
          for (x <- 0 until n ) {
            print(field(y, x) + " ")
          }
          println()
        }
        println()
      }

      for (i <- 0 until 5) {
        printWorld()
        dut.clock.step()
      }
    }
  }
}
