/*
 * Dummy tester to start a Chisel project.
 *
 * Author: Martin Schoeberl (martin@jopdesign.com)
 *
 */

package gol

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class SimpleTest extends AnyFlatSpec with ChiselScalatestTester {

  "GoL" should "produce a new cell" in {
    test(new Cell) { dut =>
      for (i <- 0 to 7) {
        dut.io.in(i).poke(false.B)
      }

      dut.io.loadVal.poke(false.B)
      dut.io.load.poke(false.B)

      dut.io.in(0).poke(true.B)
      dut.io.in(1).poke(true.B)
      dut.io.in(2).poke(true.B)
      dut.clock.step()
      dut.io.out.expect(true.B)
    }
  }
}
