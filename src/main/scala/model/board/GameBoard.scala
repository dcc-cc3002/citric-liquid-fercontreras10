package cl.uchile.dcc.citric
package model.board

import cl.uchile.dcc.citric.model.character.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/**
 *
 */
class GameBoard {
  private var chapters: Int = 0
  private val players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty
  private val panels: ArrayBuffer[Panel] = ArrayBuffer.empty
  private var currentPlayerIndex: Int = 0

  // to check: every character should be added to their home panel.
  def addCharacterToPanel(player: PlayerCharacter, panel: Panel): Unit = {
    panel.addCharacter(player)
  }

  //to check: this idk
  def removeCharacterFromPanel(player: PlayerCharacter, panel: Panel): Unit = {
    panel.removeCharacter(player)
  }

  def startTurn(player: PlayerCharacter): Unit = {
    player.rollDice()

    player.increaseStars((chapters / 5) + 1)

  }
}