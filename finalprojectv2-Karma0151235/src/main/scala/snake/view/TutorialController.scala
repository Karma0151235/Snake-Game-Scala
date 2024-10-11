package snake.view

import scalafxml.core.macros.sfxml
import scalafx.scene.control.Button
import scalafx.stage.Stage


@sfxml
class TutorialController(val primaryStage: Stage,
                         private val homeButton: Button,
                         private val gameButton: Button,
                         private val game2Button: Button) extends BaseController {

  def goHome(): Unit = {
    goToHome()
  }

  def game(): Unit = {
    goToSnakeGame()
  }

  def game2(): Unit = {
    goToSnakeGame2()
  }

}