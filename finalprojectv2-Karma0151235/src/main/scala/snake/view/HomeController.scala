package snake.view

import scalafxml.core.macros.sfxml
import scalafx.scene.control.Button
import scalafx.stage.Stage


@sfxml
class HomeController(val primaryStage: Stage, private val startButton: Button) extends BaseController{

  def startGame(): Unit = {
    goToTutorial()
  }
}