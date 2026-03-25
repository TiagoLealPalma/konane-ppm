import scala.annotation.tailrec
import utils

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
  def play(coordFrom: Coord2D, coordTo: Coord2D): (Option[Board], List[Coord2D])
}

object GameState {
  def initiallizeBoard(rand: MyRandom, height: Int, width: Int): GameState = {

    @tailrec
    def loop(row: Int, col: Int, board: Board): Board = row match {
      case row if (row > height) => board
      case _                     => {
        // Alternate between white and black stones
        val stone: Stone =
          if ((row + col) % 2 == 0) Stone.Black else Stone.White
        val nextBoard = board + (Coord2D(row, col) -> stone)

        // Fill the row until the end, then go to next row
        if (col < width - 1) loop(row, col + 1, nextBoard)
        else loop(row + 1, 0, nextBoard)
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
  def getOpenCoords(board: Board): (List[Coord2D]) = {}

  // Devolve um random move
  def randomMove(
      openCoords: List[Coord2D],
      rand: MyRandom
  ): (Coord2D, MyRandom) = {}

  // Receives and, if allowed, executes a play
  def play(
      board: Board,
      player: Stone,
      coordFrom: Coord2D,
      coordTo: Coord2D
  ): (Option[Board], List[Coord2D]) = {}

}

object MyGame extends App {
  println("----- STARTING GAME -----")
  val initialGame = GameState.initiallizeBoard(MyRandom(11111111), 10, 11)
  initialGame.draw()
}
