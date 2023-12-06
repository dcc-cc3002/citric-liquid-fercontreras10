package cl.uchile.dcc.citric
package controller

import controller.event.NormaClearEvent
import controller.observer.{NormaObserver, NormaSubject}
import controller.states.{EndGame, GameState, PreGame}
import model.board.Board
import model.character.PlayerCharacter
import model.game.TurnSystem
import view.GameLayout

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.HBox

import scala.collection.mutable.ArrayBuffer

/** The controller of the game. It is in charge of the game logic.
 *
 *  @constructor create a new game controller.
 */
class GameController extends NormaObserver[NormaClearEvent]{

  /** The stage where the game is displayed. */
  private[controller] var _gameStage: Option[PrimaryStage] = None

  /** The players in the game. */
  private[controller] val players = ArrayBuffer.empty[PlayerCharacter]

  /** The turn system of the game. */
  private val turnSystem = new TurnSystem(players)

  /** The current state of the game */
  var state: GameState = new PreGame(this)

  /** Start the game.
   */
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

  /** Responds to the event of a player reaching norma 6.
   *
   * a part of the Observer pattern. This method updates the game state
   * based on the events it observes.
   *
   * @param observable The player that reached norma 6.
   * @param event      The details of the norma clear event.
   */
  override def update(observable: NormaSubject[NormaClearEvent], event: NormaClearEvent): Unit = {
    if (event.isNormaSixReached) {
      endGame()
      state = new EndGame(this)
      println("Finished Game")
    }
  }

  /** Auxiliary method to end the game.
   */
  def endGame(): Unit = {
    players.foreach { player =>
      if (player.hasReachedNorma6) {
        println(s"${player.name}, You win!")
      } else {
        println(s"${player.name}, You lose!")
      }
    }

  }

  /** Initialize the board of the game.
   */
  def initializeBoard(): Unit = {
    val board = new Board
    board.drawBoard()
  }

  /** Randomize the order of the players. */
  def initializePlayersOrder(): Unit = {
    turnSystem.playersOrder()
  }

  /** Turn of the next player */
  def nextPlayerTurn(): Unit = {
    turnSystem.nextPlayerTurn()
  }

  /** Current player turn */
  def currentPlayer: PlayerCharacter = {
    turnSystem.currentPlayer
  }

  /*def movePlayer(diceResult: Int): Unit = {
    val currentPlayer = turnSystem.currentPlayer
    val newPosition = currentPlayer.position + diceResult
    currentPlayer.position = newPosition
    println(s"${currentPlayer.name} moved to position $newPosition")
  }*/


}