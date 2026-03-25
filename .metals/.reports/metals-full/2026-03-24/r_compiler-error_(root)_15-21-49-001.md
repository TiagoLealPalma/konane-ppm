error id: 1639B885338DBCE41FD3EDFEA570FE29
file://<WORKSPACE>/src/main/scala/main.scala
### java.lang.StringIndexOutOfBoundsException: Index 0 out of bounds for length 0

occurred in the presentation compiler.



action parameters:
offset: 1183
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
     if (row@@) 
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
java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:55)
	java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:52)
	java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:213)
	java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:210)
	java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:98)
	java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
	java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
	java.base/java.lang.String.checkIndex(String.java:4904)
	java.base/java.lang.StringLatin1.charAt(StringLatin1.java:45)
	java.base/java.lang.String.charAt(String.java:1624)
	scala.meta.internal.metals.Fuzzy.matchesName(Fuzzy.scala:293)
	scala.meta.internal.metals.Fuzzy.$anonfun$2(Fuzzy.scala:76)
	scala.meta.internal.metals.Fuzzy.$anonfun$adapted$2(Fuzzy.scala:76)
	scala.meta.internal.metals.Fuzzy.loopDelimiters$1(Fuzzy.scala:100)
	scala.meta.internal.metals.Fuzzy.genericMatches(Fuzzy.scala:127)
	scala.meta.internal.metals.Fuzzy.matches(Fuzzy.scala:77)
	dotty.tools.pc.completions.Completions.fuzzyMatcher$lzyINIT1$$anonfun$1(Completions.scala:118)
	dotty.tools.dotc.interactive.Completion$Completer.dotty$tools$dotc$interactive$Completion$Completer$$include(Completion.scala:623)
	dotty.tools.dotc.interactive.Completion$Completer$$anon$5.applyOrElse(Completion.scala:654)
	dotty.tools.dotc.interactive.Completion$Completer$$anon$5.applyOrElse(Completion.scala:653)
	scala.collection.immutable.List.collect(List.scala:268)
	scala.collection.immutable.List.collect(List.scala:79)
	dotty.tools.dotc.interactive.Completion$Completer.accessibleMembers(Completion.scala:655)
	dotty.tools.dotc.interactive.Completion$Completer.scopeCompletions$lzyINIT1$$anonfun$1(Completion.scala:405)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:619)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:617)
	dotty.tools.dotc.core.Contexts$Context$$anon$2.foreach(Contexts.scala:134)
	dotty.tools.dotc.interactive.Completion$Completer.scopeCompletions$lzyINIT1(Completion.scala:395)
	dotty.tools.dotc.interactive.Completion$Completer.scopeCompletions(Completion.scala:385)
	dotty.tools.dotc.interactive.Completion$.computeCompletions(Completion.scala:242)
	dotty.tools.dotc.interactive.Completion$.rawCompletions(Completion.scala:87)
	dotty.tools.pc.completions.Completions.enrichedCompilerCompletions(Completions.scala:122)
	dotty.tools.pc.completions.Completions.completions(Completions.scala:147)
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:139)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:197)
	scala.meta.internal.pc.CompilerAccess.withSharedCompiler(CompilerAccess.scala:149)
	scala.meta.internal.pc.CompilerAccess.$anonfun$1(CompilerAccess.scala:93)
	scala.meta.internal.pc.CompilerAccess.onCompilerJobQueue$$anonfun$1(CompilerAccess.scala:210)
	scala.meta.internal.pc.CompilerJobQueue$Job.run(CompilerJobQueue.scala:153)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
	java.base/java.lang.Thread.run(Thread.java:1474)
```
#### Short summary: 

java.lang.StringIndexOutOfBoundsException: Index 0 out of bounds for length 0