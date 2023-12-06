package cl.uchile.dcc.citric
package controller

import controller.event.NormaClearEvent
import controller.observer.{NormaObserver, NormaSubject}
import controller.states.{Chapter, EndGame, GameState, PreGame}
import model.board.Board
import model.character.PlayerCharacter
import model.game.TurnSystem
import view.GameLayout

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.HBox

import scala.collection.mutable.ArrayBuffer

class GameController extends NormaObserver[NormaClearEvent]{

  /** The stage where the game is displayed. */
  private var _gameStage: Option[PrimaryStage] = None

  /** The players in the game. */
  private[controller] val players = ArrayBuffer.empty[PlayerCharacter]

  /** The turn system of the game. */
  private val turnSystem = new TurnSystem(players)

  /** The current state of the game */
  var state: GameState = new PreGame(this)

  def startGame(): Unit = {
    _gameStage = Some(new PrimaryStage {
      title = "99.7% Citric Liquid"
      width = 700
      height = 600
      scene = new Scene {
        root = new HBox {
          style = "-fx-background-color: #dbdcff;"
          children = Seq(
            GameLayout.createMainLayout()
          )
        }
      }
    })
    println("Game Started")
  }

  override def update(observable: NormaSubject[NormaClearEvent], event: NormaClearEvent): Unit = {
    if (event.isNormaSixReached) {
      endGame()
      state = new EndGame(this)
      println("Finished Game")
    }
  }

  def endGame(): Unit = {
    players.foreach { player =>
      if (player.hasReachedNorma6) {
        println(s"${player.name}, You win!")
      } else {
        println(s"${player.name}, You lose!")
      }
    }

  }

  def initializeBoard(): Unit = {
    val board = new Board
    board.drawBoard()
  }

  def initializePlayersOrder(): Unit = {
    turnSystem.playersOrder()
  }

  def nextPlayerTurn(): Unit = {
    turnSystem.nextPlayerTurn()
  }

  def currentPlayer: PlayerCharacter = {
    turnSystem.currentPlayer
  }

  def movePlayer(diceResult: Int): Unit = {

  }

}