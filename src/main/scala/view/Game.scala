package cl.uchile.dcc.citric
package view

import controller.GameController
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{BorderPane, VBox}

object Game extends JFXApp3 {

  private val gameController = new GameController()

  override def start(): Unit = {
    stage = new PrimaryStage {
      title = "99.7% Citric Liquid"
      width = 700
      height = 600
      resizable = false
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

    startButton.onAction = _ => gameController.startGame()
    exitButton.onAction = _ => exitGame()

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

  private def exitGame(): Unit = {
    stage.close()
  }
}