package konane
import scala.annotation.tailrec
import scala.collection.parallel.ParMap


case class GameState(
    board: Board,
    turn: Stone,
    rand: MyRandom,
    height: Int,
    width: Int
) {
  val openCoords = GameState.getOpenCoords(board)

  def draw: Unit = GameState.draw(board, height, width)
  def randomMove(): (Coord2D, MyRandom) = GameState.randomMove(openCoords, rand)
  def play(from: Coord2D, to: Coord2D): (Option[Board], List[Coord2D]) = GameState.play(board, turn, from, to)
}

object GameState {
  def initiallizeBoard(rand: MyRandom, height: Int, width: Int): GameState = {
    val middleW: Int = width / 2  - 1
    val middleH: Int = height / 2
    println(middleW)
    println(middleH)

    @tailrec
    def loop(row: Int, col: Int, board: Board): Board = row match {
      case row if (row > height) => board
      case _                     => {
        // Alternate between white and black stones
        val stone: Stone =
          if ((row + col) % 2 == 0) Stone.Black else Stone.White

        val newBoard = {
          if (row == middleH  && (col == middleW || col == middleW + 1)) // Initial open slots
            board
          else
            board + (Coord2D(row, col) -> stone)
        }

        // Fill the row until the end, then go to next row
        if (col < width - 1) loop(row, col + 1, newBoard)
        else loop(row + 1, 0, newBoard)
      }
    }

    val board = loop(0, 0, ParMap.empty)
    GameState(board, Stone.White, rand, height, width)
  }

  def draw(board: Board, height: Int, width: Int): Unit = {

    @tailrec
    def loop(row: Int, col: Int): Unit = {
      if (row >= height) return

      // Print slot to console
      val stone: Option[Stone] = board.get(Coord2D(row, col))
      val display = stone match {
        case None              => " "
        case Some(Stone.White) => "W"
        case Some(Stone.Black) => "B"
      }

      print(f"[${display}]")

      if (col < width - 1) loop(row, col + 1)
      else { println(); loop(row + 1, 0) }
    }

    loop(0, 0)
  }

  // Devolve a lista de coordenadas livres (Sem peças)
  def getOpenCoords(board: Board, height: Int, width: Int): (List[Coord2D]) = {
    
    @tailrec
    def loop(row: Int, col: Int):List[Coord2D] = {
      
    }
    
    loop(0,0)
  }

  // Devolve um random move
  def randomMove(
      openCoords: List[Coord2D],
      rand: MyRandom
  ): (Coord2D, MyRandom) = {
    //TODO
    (Coord2D(-1,-1), rand)
     }

  // Receives and, if allowed, executes a play
  def play(
      board: Board,
      player: Stone,
      coordFrom: Coord2D,
      coordTo: Coord2D
  ): (Option[Board], List[Coord2D]) = {
    //TODO
    (None,Nil)
  }

}

object MyGame extends App {
  println("----- STARTING GAME -----")
  val initialGame = GameState.initiallizeBoard(MyRandom(11111111), 6, 7)
  initialGame.draw
}
