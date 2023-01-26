package gol

import scala.io.Source
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

  def worldFromFile(name: String): (Int, Array[Array[Int]]) = {

    val input = Source
      .fromFile(name)
      .getLines()
      .toSeq
      .head
      .split(" ")

    val n = input.head.toInt

    n -> input
      .drop(2)
      .map(_.toInt)
      .grouped(n)
      .toArray
  }
}
