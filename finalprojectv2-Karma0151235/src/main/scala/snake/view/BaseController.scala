package snake.view

import scalafx.stage.Stage

trait BaseController {
  def primaryStage: Stage

  def goToHome(): Unit = snake.SnakeApp.Home()
  def goToSnakeGame(): Unit = snake.SnakeApp.SnakeGame()
  def goToSnakeGame2(): Unit = snake.SnakeApp.SnakeGame2()
  def goToTutorial(): Unit = snake.SnakeApp.goTutorial()
}
