package snake.view

import scalafx.animation.AnimationTimer
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.Button
import scalafx.Includes._
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.stage.Stage
import scalafx.application.Platform

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

abstract class SnakeGame extends BaseController {
  val primaryStage: Stage
  val gameCanvas: Canvas
  val restartButton: Button
  val gameOverText: Text
  val scoreText: Text
  val homeButton: Button

  protected val gc: GraphicsContext = gameCanvas.graphicsContext2D
  protected val cellSize: Int = 20
  protected val width: Int = 800 / cellSize
  protected val height: Int = 386 / cellSize

  protected var snakearray: ArrayBuffer[(Int, Int)] = ArrayBuffer((5, 5))
  protected var direction: (Int, Int) = (1, 0)
  protected var food: (Int, Int) = newFood()
  protected var gameOver: Boolean = false
  protected var score: Int = 0

  protected var frameRate: Int = 10
  protected var updateInterval: Long = 1000000000 / frameRate

  protected var lastUpdateTime: Long = System.nanoTime()

  protected val timer: AnimationTimer = AnimationTimer { now =>
    if (!gameOver) {
      val currentTime = System.nanoTime()
      if (currentTime - lastUpdateTime >= updateInterval) {
        lastUpdateTime = currentTime
        update()
        draw()
      }
    }
  }

  def initialize(): Unit = {
    restartGame()
    Platform.runLater(() => gameCanvas.requestFocus())
    gameCanvas.requestFocus()
    gameCanvas.onKeyPressed = (e: KeyEvent) => handleKeyPress(e)
    timer.start()
    restartButton.onMouseClicked = _ => gameCanvas.requestFocus()
    restartButton.focusTraversable = false
    homeButton.onMouseClicked = _ => gameCanvas.requestFocus()
    homeButton.focusTraversable = false
  }

  def handleKeyPress(e: KeyEvent): Unit = {
    e.code match {
      case KeyCode.Up if direction != (0, 1) => direction = (0, -1)
      case KeyCode.Down if direction != (0, -1) => direction = (0, 1)
      case KeyCode.Left if direction != (1, 0) => direction = (-1, 0)
      case KeyCode.Right if direction != (-1, 0) => direction = (1, 0)
      case _ =>
    }
  }

  def update(): Unit

  def draw(): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, gameCanvas.width.value, gameCanvas.height.value)

    gc.fill = Color.Green
    snakearray.foreach { case (x, y) =>
      gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize)
    }

    gc.fill = Color.Red
    gc.fillRect(food._1 * cellSize, food._2 * cellSize, cellSize, cellSize)
  }

  def newFood(): (Int, Int) = {
    var newFood: (Int, Int) = (Random.nextInt(width), Random.nextInt(height))
    while (snakearray.contains(newFood)) {
      newFood = (Random.nextInt(width), Random.nextInt(height))
    }
    newFood
  }

  def restartGame(): Unit

}