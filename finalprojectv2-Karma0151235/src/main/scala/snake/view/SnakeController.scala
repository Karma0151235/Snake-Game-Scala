package snake.view

import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.Button
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.text.Text
import scalafx.stage.Stage

import scala.collection.mutable.ArrayBuffer

import scalafxml.core.macros.sfxml

@sfxml
class SnakeController(val primaryStage: Stage,
                      val gameCanvas: Canvas,
                      val restartButton: Button,
                      val gameOverText: Text,
                      val scoreText: Text,
                      val homeButton: Button) extends SnakeGame {

  initialize()


  override def update(): Unit = {
    val (x, y) = snakearray.head
    val newHead = ((x + direction._1 + width) % width, (y + direction._2 + height) % height)

    if (snakearray.contains(newHead)) {
      gameOver = true
      gameOverText.visible = true
    } else {
      snakearray.prepend(newHead)
      if (newHead == food) {
        food = newFood()
        score += 10
        scoreText.text = s"Score: $score"
      } else {
        snakearray.remove(snakearray.length - 1)
      }
    }
  }

  override def restartGame(): Unit = {
    snakearray = ArrayBuffer((5, 5))
    direction = (1, 0)
    food = newFood()
    gameOver = false
    gameOverText.visible = false
    score = 0
    scoreText.text = s"Score: $score"
    gameCanvas.requestFocus()
  }

  def goHome(): Unit = {
    goToHome()
    gameCanvas.requestFocus()
  }
}