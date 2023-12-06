package cl.uchile.dcc.citric
package view

import model.game.{Board, Movement, PlayerCircle}

import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.text.{Font, Text}

import scala.util.Random

object GameLayout {

  val player = new PlayerCircle
  val boardSize = 8
  val board = new Board(boardSize)
  private val movement = new Movement(player, board)

  private def createGameBoard(): VBox = {
    val vbox = new VBox {
      children = Seq(board.gridPane)
    }
    vbox
  }




  private def createPlayersBox(): VBox = {
    val playersBox = new VBox(10)

    // Add players with different background colors
    val players = Seq(
      ("Player 1", "#C9E4DE"),
      ("Player 2", "#F2C6DE"),
      ("Player 3", "#C6DEF1"),
      ("Player 4", "#F7D9C4")
    )

    for ((name, color) <- players) {

      val playerBox = new VBox(5) {
        style = s"-fx-background-color: $color;"
        alignment = scalafx.geometry.Pos.Center
        children = Seq(
          new Text {
            text = name
            font = Font("Arial", 14)
          },
          new Text {
            text = "Current HP: 100" // You can update this with the actual HP value
            font = Font("Arial", 12)
          }
        )
      }

      playersBox.children.add(playerBox)
    }

    // Center the players box
    playersBox.alignment = scalafx.geometry.Pos.Center
    VBox.setMargin(playersBox, scalafx.geometry.Insets(50, 0, 0, 0))

    playersBox
  }

  def rollDice(): Int = {
    val random = new Random()
    random.nextInt(6) + 1 // Assuming a standard six-sided dice
  }

  private def createButtons(): VBox = {
    //val startButton = new Button("Start Turn")
    //val endButton = new Button("End Turn")
    val rollButton = new Button("Roll Dice")

    val diceLabel = new scalafx.scene.control.Label()

    // Handle button click to roll the dice
    rollButton.onAction = _ => {
      val result = rollDice()
      diceLabel.text = s"Result: $result"

      // Move the player automatically
      movement.movePlayer(result)
    }

    new VBox(10) {
      //children = Seq(startButton, endButton, rollButton, diceLabel)
      children = Seq(rollButton, diceLabel)
    }
  }

  def createMainLayout(): HBox = {
    val gameBoardVBox = createGameBoard()
    val playersBox = createPlayersBox()
    val buttonsVBox = createButtons()

    new HBox(30) {
      style = "-fx-padding: 30;"
      children = Seq(
        new HBox(10) {
          children = Seq(
            gameBoardVBox
          )
          padding = scalafx.geometry.Insets(15, 2, 0, 0)
        },
        new VBox(10) {
          children = Seq(
            playersBox,
            buttonsVBox
          )
        }
      )
    }
  }
}
