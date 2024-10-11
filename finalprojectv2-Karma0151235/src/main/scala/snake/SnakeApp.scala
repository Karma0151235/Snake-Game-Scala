package snake

import javafx.application.Platform
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

object SnakeApp extends JFXApp {
  // Load the FXML resource for the welcome page
  val resource = getClass.getResource("view/Home.fxml")
  val loader = new FXMLLoader(resource, NoDependencyResolver)
  loader.load()

  // Get the root element from the loaded FXML
  val root = loader.getRoot[javafx.scene.layout.AnchorPane]

  // Set up the primary stage (window)
  stage = new JFXApp.PrimaryStage {
    title = "ScalaFX Snake Game"
    scene = new Scene(root)
    onCloseRequest = _ => Platform.exit() // Ensure the application exits properly
  }

  private var primaryStage = stage
  def loadScene(fxmlFile: String): Unit = {
    try {
      val resource = getClass.getResource(fxmlFile)
      if (resource == null) {
        throw new RuntimeException(s"$fxmlFile not found")
      }

      val loader = new FXMLLoader(resource, NoDependencyResolver)
      loader.load()
      val root = loader.getRoot[javafx.scene.layout.AnchorPane]

      // Update the existing stage's scene
      primaryStage.scene = new Scene(root)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        println(s"Error loading $fxmlFile: ${e.getMessage}")
    }
  }
  def SnakeGame() = {
    loadScene("view/Snake.fxml")
  }

  def SnakeGame2() = {
    loadScene("view/Snake2.fxml")
  }

  def Home() = {
    loadScene("view/Home.fxml")
  }

  def goTutorial() ={
    loadScene("view/Tutorial.fxml")
  }
}
