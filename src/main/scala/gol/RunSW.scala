package gol

object RunSW extends App {

  val l = List(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
  for (n <- l) {
    var iter = 100000
    var time = 0L
    while (time < 1000) {
      // println(s"time = $time, $iter")
      val start = Util.getRandomWorld(n)
      val gol = new GoL(start)
      val startTime = System.currentTimeMillis()
      for (i <- 0 until iter) {
        gol.step()
      }
      time = System.currentTimeMillis() - startTime
      iter = iter << 1
    }
    // println(s"$n x $n world, $iter iterations, execution time: $time ms")
    println(s"$n x $n & ${time * 1000.0 / iter} us\\\\")
  }

}
