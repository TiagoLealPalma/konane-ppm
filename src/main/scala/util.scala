package konane

import scala.collection.parallel.ParMap
import scala.util.Random

case class Coord2D(row: Int, col: Int) // (row,col)
type Board = ParMap[Coord2D, Stone]

//Enum in Scala 3
enum Stone:
  case Black, White

case class MyRandom(seed: Long) {

  def nextInt: (Int, MyRandom) = {
    val newSeed = (seed * 0x5deece66dL + 0xbL) &
      0xffffffffffffL
    val nextRandom = MyRandom(newSeed)
    val n = (newSeed >>> 16).toInt

    (n, nextRandom)
  }

  def nextIntBounded(bound: Int): (Int, MyRandom) = {
    val (n, nextGen) = this.nextInt
    (math.abs(n) % bound, nextGen)
  }

}
