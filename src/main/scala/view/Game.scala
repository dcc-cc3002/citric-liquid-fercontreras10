package cl.uchile.dcc.citric
package view

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{BorderPane, VBox}
import scalafx.scene.paint.Color

object Game extends JFXApp3 {

  //private val gameController = new GameController()

  override def start(): Unit = {
    stage = new PrimaryStage {
      title = "99.7% Citric Liquid"
      width = 600
      height = 600
      scene = new Scene {
        root = new BorderPane {
          style = "-fx-background-color: #dbdcff;"
          center = createButtonPanel()
        }
      }
    }
  }

  private def createButtonPanel(): VBox = {
    val startButton = new Button("Start")
    val exitButton = new Button("Exit")

    startButton.onAction = _ => startGame()
    exitButton.onAction = _ => exitApp()

    new VBox(20, createLabel(),
      new VBox(10, startButton, exitButton) {
        alignment = scalafx.geometry.Pos.Center
      }){
      alignment = scalafx.geometry.Pos.Center
    }
  }

  private def createLabel(): Label = {
    new Label("99.7% Citric Liquid") {
      style = "-fx-font-size: 18pt; -fx-font-family: 'Verdana'; -fx-font-weight: bold;"
    }
  }

  private def startGame(): Unit = {
    val gameStage = new PrimaryStage {
      title = "99.7% Citric Liquid"
      width = 600
      height = 600
      scene = new Scene {
        fill = Color.web("#dbdcff")
        content = new Label("Game Content Goes Here")
      }
    }

    gameStage.show()
    //gameStage.showAndWait()
  }

  private def exitApp(): Unit = {
    stage.close()
  }
}