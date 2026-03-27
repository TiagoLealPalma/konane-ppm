//> using jvm 25
//> using scala 3.5.0
//> using dep org.scala-lang.modules::scala-parallel-collections:1.0.4

package konane
import scala.annotation.tailrec
import scala.collection.parallel.ParMap
import java.io.StreamCorruptedException

case class GameState(
    board: Board,
    turn: Stone,
    rand: MyRandom,
    height: Int,
    width: Int
) {
  val openCoords: List[Coord2D] = GameState.getOpenCoords(board, height, width)

  def draw: Unit = GameState.draw(board, height, width)
  def randomMove(): (Coord2D, MyRandom) = GameState.randomMove(openCoords, rand)
  def play(from: Coord2D, to: Coord2D): (Option[Board], List[Coord2D]) =
    GameState.play(board, turn, from, to, openCoords)
  def playRandomly(): (Option[Board], MyRandom, List[Coord2D]) =
    GameState.playRandomly(
      board,
      rand,
      turn,
      openCoords,
      GameState.randomMove
    )
}

object GameState {
  def initiallizeBoard(rand: MyRandom, height: Int, width: Int): GameState = {
    val middleW: Int = width / 2 - 1
    val middleH: Int = height / 2

    @tailrec
    def loop(row: Int, col: Int, board: Board): Board = row match {
      case row if (row > height) => board
      case _                     => {
        // Alternate between white and black stones
        val stone: Stone =
          if ((row + col) % 2 == 0) Stone.Black else Stone.White

        val newBoard = {
          if (
            row == middleH && (col == middleW || col == middleW + 1)
          ) // Initial open slots
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
    GameState(board, Stone.Black, rand, height, width)
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

      if (col == 0) {
        if (row == 0)
          println(
            (0 until width).foldLeft("  ")((acc, col) => acc + s" ${col} ")
          )
        print(f"${row} ")
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
    def loop(row: Int, col: Int, acc: List[Coord2D]): List[Coord2D] = {
      if (row >= height) return acc

      val coord = Coord2D(row, col)
      val stone: Option[Stone] = board.get(coord)

      val newLst = stone match {
        case None    => coord :: acc
        case Some(_) => acc
      }

      if (col < width - 1) loop(row, col + 1, newLst)
      else loop(row + 1, 0, newLst)
    }

    loop(0, 0, List())
  }

  // Devolve um random move
  def randomMove(
      openCoords: List[Coord2D],
      rand: MyRandom
  ): (Coord2D, MyRandom) = {
    val r: (Int, MyRandom) = rand.nextIntBounded(openCoords.length)
    val coord = openCoords(r._1)
    val newRand = r._2

    (coord, newRand)
  }

  // Receives and, if allowed, executes a play
  def play(
      board: Board,
      player: Stone,
      from: Coord2D,
      to: Coord2D,
      openCoords: List[Coord2D]
  ): (Option[Board], List[Coord2D]) = {
    // verificar:
    // 1. from tem peça (FALTA VERIFICAR SE piece == turn)
    // 2. to tem de pertencer a openCoords
    // 3. Distancia entre from e to tem de ser igual a 2 num so eixo
    // 4. slot entre from e tu tem uma oposite color

    val notAllowed = (None, openCoords)

    // 1.
    val fromStone = board.get(from)
    if (fromStone.isEmpty) return notAllowed

    // 2.
    if (!openCoords.exists(x => x == to)) return notAllowed

    // 3. (Pode ser substituido pelo inBetween com um Int check)
    val deltaY = math.abs(from._1 - to._1)
    val deltaX = math.abs(from._2 - to._2)
    if (deltaX + deltaY != 2 || deltaX == 1) return notAllowed

    // 4.
    val midPoint = inBetween(from, to)
    val midPointStone = board.get(midPoint)

    if (midPointStone.isEmpty || midPointStone.get == fromStone.get)
      return notAllowed

    // Executar jogada
    println()
    println()
    println(f"Jogada selecionada: From = (${from})  To = (${to})")
    val newBoard = board - midPoint - from + (to -> fromStone.get)
    val newOpenCoords = from :: midPoint :: openCoords.filter(x => x != to)
    (Some(newBoard), newOpenCoords)
  }

  // (3,2) (5,2) = (4,2)
  def inBetween(from: Coord2D, to: Coord2D): Coord2D = {
    Coord2D((from._1 + to._1) / 2, (from._2 + to._2) / 2)
  }

  def playRandomly(
      board: Board,
      rand: MyRandom,
      player: Stone,
      openCoords: List[Coord2D],
      f: (List[Coord2D], MyRandom) => (Coord2D, MyRandom)
  ): (Option[Board], MyRandom, List[Coord2D]) = {

    val noMovesLeft = (None, rand, openCoords)
    val playableEmptySlots: List[Coord2D] = openCoords

    @tailrec
    def loop(
        playableSlots: List[Coord2D]
    ): (Option[Board], MyRandom, List[Coord2D]) = {
      if (playableSlots.length == 0) {
        val winner = player match {
          case Stone.Black => Stone.White
          case Stone.White => Stone.Black
        }

        println("==========================")
        println(f"${winner} ganhou!")
        println("==========================")
        return noMovesLeft
      }

      val (Coord2D(openY: Int, openX: Int), rand1) = f(playableSlots, rand)

      // Depois de opter openSpot temos de:
      // 1. Iterar sobre as quatro peças possiveis e chama isAllowed()
      // 1.1 verificar se a peça que la esta é a do jogador
      // 1.2 verificar se a jogada é legal
      val possibleSpots = List(
        Coord2D(openY + 2, openX),
        Coord2D(openY - 2, openX),
        Coord2D(openY, openX + 2),
        Coord2D(openY, openX - 2)
      )

      val allowedPlays: List[Coord2D] =
        possibleSpots.filter(x =>
          isAllowed(board, x, Coord2D(openY, openX), player)
        )

      allowedPlays.length match {
        case 0 =>
          loop(playableSlots.filter(x => x != Coord2D(openY, openX)))
        case _ => {
          val (choosenIndex, rand2) = rand1.nextIntBounded(allowedPlays.length)
          val choosenMove = allowedPlays(choosenIndex)
          val (newBoard, newOpenCoords) =
            play(board, player, choosenMove, Coord2D(openY, openX), openCoords)

          (newBoard, rand2, newOpenCoords)
        }
      }
    }
    loop(playableEmptySlots)
  }

  def isAllowed(
      board: Board,
      from: Coord2D,
      to: Coord2D,
      player: Stone
  ): Boolean = {
    val fromStone = board.get(from)
    if (fromStone.isEmpty || fromStone.get != player) return false

    val midPoint = inBetween(from, to)
    val midPointStone = board.get(midPoint)

    if (midPointStone.isEmpty || midPointStone.get == fromStone.get)
      return false

    true
  }
}

object MyGame extends App {
  println("----- STARTING GAME -----")
  val initialGame =
    GameState.initiallizeBoard(MyRandom(11111113), 6, 6)
  initialGame.draw

  def gameLoop(gameState: GameState, numPlaysLeft: Int): Int =
    numPlaysLeft match {
      case 0 => 1
      case _ => {
        val (newBoard, newRand, openCoords) = gameState.playRandomly()
        val newTurn = gameState.turn match {
          case Stone.Black => Stone.White
          case Stone.White => Stone.Black
        }
        val newGameState = gameState.copy(newBoard.get, newTurn, newRand)
        newGameState.draw
        gameLoop(newGameState, numPlaysLeft - 1)
      }
    }

  gameLoop(initialGame, 30)
}
