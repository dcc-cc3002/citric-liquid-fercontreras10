package cl.uchile.dcc.citric
package model.board

import model.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/**
 *
 */
class GameBoard {
  private val panels: ArrayBuffer[Panel] = ArrayBuffer.empty

  def addCharacterToPanel(player: PlayerCharacter, panel: Panel): Unit = {
    panel.addCharacter(player)
  }

  def removeCharacterFromPanel(player: PlayerCharacter, panel: Panel): Unit = {
    panel.removeCharacter(player)
  }
}