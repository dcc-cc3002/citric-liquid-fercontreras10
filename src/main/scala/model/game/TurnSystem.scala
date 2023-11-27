package cl.uchile.dcc.citric
package model.game

import model.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** The `TurnSystem` class represents the system that controls the turns of the players.
 *
 * @param players the players of the game.
 *
 * @author [[https://github.com/fercontreras10/ Fernanda Contreras C.]]
 */
class TurnSystem(players: List[PlayerCharacter]) {

  /** Represents the order in which the players will play. */
  private[model] val playerOrder: ArrayBuffer[PlayerCharacter] = ArrayBuffer(players: _*)
  /** Random number generator. */
  private val random = new Random()
  /** Represents the index of the current player in the playerOrder array. */
  private var currentPlayerIndex: Int = 0
  /** Counter of every level of the game. */
  private var _chapters: Int = 1

  /** Returns the current chapter of the players. */
  private[model] def chapters: Int = _chapters

  /** Starts the game by assigning the players order in aleatory way, the order remains the same for the rest of the game.
   *
   * This method is invoked when the game starts
   */
  def startGame(): Unit = {
    playerOrder.clear()
    val remainingPlayers = ArrayBuffer(players: _*)
    while (remainingPlayers.nonEmpty) {
      val nextPlayer = remainingPlayers.remove(random.nextInt(remainingPlayers.length))
      playerOrder += nextPlayer
    }
    _chapters = 1

    // Pass a reference to this TurnSystem to each PlayerCharacter
    playerOrder.foreach(_.setTurnSystem(this))
  }

  /** Returns the current player. */
  def currentPlayer: PlayerCharacter = playerOrder(currentPlayerIndex)

  /** Adds stars to the player when their turn start.
   *
   * The amount added is floor(_chapters / 5).toInt + 1
   *
   * This method is invoked when the turn of the player starts.
   *
   * @param player the player that will receive the stars.
   */
  private def addStarsToPlayer(player: PlayerCharacter): Unit = {
    val addStars = Math.floor(_chapters / 5).toInt + 1
    player.stars += addStars
  }

  /** Advances to the turn of the next player.
   * The order is done in a circular way.
   */
  def nextPlayerTurn(): Unit = {
    addStarsToPlayer(currentPlayer)

    currentPlayerIndex = (currentPlayerIndex + 1) % playerOrder.length

    if (currentPlayerIndex == 0) {
      _chapters += 1
    }
  }

}
