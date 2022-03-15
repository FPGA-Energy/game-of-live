package gol

import scala.util.Random

object Util {
  def getRandomWorld(n: Int) = {
    val start = Array.ofDim[Int](n, n)
    val r = new Random()
    for (y <- 0 until n) {
      for (x <- 0 until n) {
        start(y)(x) = if (r.nextBoolean()) 1 else 0
      }
    }
    start
  }
}
