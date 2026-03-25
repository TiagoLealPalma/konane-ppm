package utils
import random

type Coord2D = (Int, Int) //(row, column)
type Board = ParMap[Coord2D, Stone]

//Enum in Scala 3
enum Stone:
case Black, White


case class MyRandom(seed: Long) extends Random {
  def nextInt: (Int, Random)  = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) &
    0xFFFFFFFFFFFFL
    val nextRandom = MyRandom(newSeed)
    val n = (newSeed >>> 16).toInt
   
    (n, nextRandom)
  }
}
