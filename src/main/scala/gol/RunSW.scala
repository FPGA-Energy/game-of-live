package gol

object RunSW extends App {

  val n = 40
  val iter = 25000000
  val start = Util.getRandomWorld(n)
  val gol = new GoL(start)
  val startTime = System.currentTimeMillis()
  for (i <- 0 until iter) {
    gol.step()
  }
  val time = System.currentTimeMillis() - executionStart
  println(s"$n x $n world, $iter iterations, execution time: $time ms")
}
