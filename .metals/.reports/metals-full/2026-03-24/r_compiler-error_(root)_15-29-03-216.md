error id: BF12080A9AB1DF26BE0D32002F7EF42F
file://<WORKSPACE>/src/main/scala/main.scala
### dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Ident(Some) is not assigned

occurred in the presentation compiler.



action parameters:
offset: 1381
uri: file://<WORKSPACE>/src/main/scala/main.scala
text:
```scala
import util.scala
import scala.annotation.tailrec

case class GameState(board: Board, turn: Stone, rand: MyRandom, height: Int, width: Int) {
  val openCoords = GameState.getOpenCoords(board)

  def randomMove():(Coord2D, MyRandom) = GameState.randomMove(openCoords, rand)
  def play(coordFrom: Coord2D, coordTo: Coord2D):(Option[Board], List[Coord2D])
}

object GameState {
  def initiallizeBoard(rand: MyRandom, height: Int, width: Int) :GameState = {
    
    @tailrec
    def loop(row: Int, col: Int, board: Board): Board = row match {
      case row > height => board
      case _ => {
        // Alternate between white and black stones
        val stone: Stone = if ((row + col) % 2 == 0) Stone.Black else Stone.White
        val nextBoard = board + (Coord2D(row, col) -> stone)

        // Fill the row until the end, then go to next row
        if (col < width - 1)  loop(row, col + 1, nextBoard) 
        else                  loop(row + 1, 0, nextBoard)
      }
    }

    val board = loop(0,0, ParMap.empty)
    GameState(board, Stone.White, rand, height, width)
  }


  def draw(board: Board):Unit = {

    @tailrec
    def loop(row: Int, col: Int):Unit = {
      if (row >= height) return

      // Print slot to console
      val stone: Option[Stone] = board.get(Coord2D(row, col)) 
      display = stone match {
        case None => print(f"[ ]")
        case Some[@@]    => print(f"[${stone}]")
      
      if (col < width - 1)  loop(row, col + 1) 
      else                  loop(row + 1, 0)
    }
  }

  // Devolve a lista de coordenadas livres (Sem peças)
  def getOpenCoords(board: Board):(List[Coord2D]) = {

  }

  // Devolve um random move
  def randomMove(openCoords: List[Coord2D], rand: MyRandom):(Coord2D, MyRandom) = {

  }

  // Receives and, if allowed, executes a play
  def play(board: Board, player: Stone, coordFrom: Coord2D, coordTo: Coord2D):
  (Option[Board], List[Coord2D])                                                = {


  }

}




object MyGame extends App {
  println("----- STARTING GAME -----")
}

```


presentation compiler configuration:
Scala version: 3.3.7-bin-nonbootstrapped
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-l5R6cozSTpqH7rOemD1rgg== [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/sourcegraph/semanticdb-javac/0.11.2/semanticdb-javac-0.11.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.7/scala3-library_3-3.3.7.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parallel-collections_3/1.2.0/scala-parallel-collections_3-1.2.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.16/scala-library-2.13.16.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>




#### Error stacktrace:

```
dotty.tools.dotc.ast.Trees$Tree.tpe(Trees.scala:74)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:208)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:104)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:88)
	dotty.tools.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:46)
	dotty.tools.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:498)
	scala.meta.internal.pc.CompilerAccess.withSharedCompiler(CompilerAccess.scala:149)
	scala.meta.internal.pc.CompilerAccess.withNonInterruptableCompiler$$anonfun$1(CompilerAccess.scala:133)
	scala.meta.internal.pc.CompilerAccess.onCompilerJobQueue$$anonfun$1(CompilerAccess.scala:210)
	scala.meta.internal.pc.CompilerJobQueue$Job.run(CompilerJobQueue.scala:153)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
	java.base/java.lang.Thread.run(Thread.java:1474)
```
#### Short summary: 

dotty.tools.dotc.ast.Trees$UnAssignedTypeException: type of Ident(Some) is not assigned