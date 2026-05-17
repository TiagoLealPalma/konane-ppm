package konane

import javafx.fxml.FXML
import javafx.scene.layout.GridPane
import javafx.scene.shape.{Circle, Rectangle}
import javafx.scene.paint.Color
import javafx.geometry.{HPos, VPos}
import javafx.scene.control.Label

class Controller {
  var grid: GridPane = _
  var gameState: GameState = _
  var selected: Option[Coord2D] = None
  var turnLabel: Label = _
  var errorLabel: Label = _

  def renderBoard(): Unit = {
    turnLabel.setText(gameState.turn match
      case Stone.Black => "Black's turn"
      case Stone.White => "White's turn"
    )
    grid.getChildren.clear()

    (0 until gameState.height).flatMap(row => (0 until gameState.width).map(col => (row, col)))
      .foreach { (row, col) =>
        val rect = new Rectangle(60, 60)
        rect.setFill(if (row + col) % 2 == 0 then Color.BURLYWOOD else Color.SADDLEBROWN)
        rect.setOnMouseClicked(_ => {
          if selected.isDefined then {
            val (newBoard, newOpenCoords) = gameState.play(selected.get, Coord2D(row, col))
            if newBoard.isDefined then {
              val nextTurn = if gameState.turn == Stone.Black then Stone.White else Stone.Black
              gameState = gameState.copy(board = newBoard.get, turn = nextTurn)
              selected = None
              errorLabel.setText("")
              renderBoard()
            }
          }
        })
        GridPane.setRowIndex(rect, row)
        GridPane.setColumnIndex(rect, col)
        grid.getChildren.add(rect)
      }

    gameState.board.seq.foreach { case (Coord2D(row, col), stone) =>
      val circle = new Circle(22)
      stone match {
        case Stone.Black =>
          circle.setFill(Color.BLACK)
        case Stone.White =>
          circle.setFill(Color.WHITE)
          circle.setStroke(Color.BLACK)
      }
      circle.setOnMouseClicked(_ => {
        if stone == gameState.turn then
          errorLabel.setText("")
          selected = Some(Coord2D(row, col))
        else
          val colorName = if gameState.turn == Stone.Black then "Black" else "White"
          errorLabel.setText(s"Click a $colorName stone")
      })
      GridPane.setRowIndex(circle, row)
      GridPane.setColumnIndex(circle, col)
      GridPane.setHalignment(circle, HPos.CENTER)
      GridPane.setValignment(circle, VPos.CENTER)
      grid.getChildren.add(circle)
    }
  }
}
