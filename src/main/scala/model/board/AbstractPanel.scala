package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Implements the common behavior of all panels.
 */
abstract class AbstractPanel extends Panel{

  val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]

  var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]

  def addCharacter(player: PlayerCharacter): Unit = {
    characters += player
  }

  def removeCharacter(player: PlayerCharacter): Unit = {
    characters -= player
  }

  def addNextPanel(panel: Panel): Unit = {
    nextPanels += panel
  }

  def removeNextPanel(panel: Panel): Unit = {
    nextPanels -= panel
  }

  /*def startCombat(player: PlayerCharacter, startCombat: Boolean): Unit = {
    if (characters.nonEmpty) {
      if (startCombat) {
        val otherPlayers = characters.filter(_ != player)
        if (otherPlayers.nonEmpty) {
          val opponent = otherPlayers(scala.util.Random.nextInt(otherPlayers.length))
          player.playerCombat(player, opponent)
          if (opponent.stateKO) {
            removeCharacter(opponent)
          }
          else if (player.stateKO) {
            removeCharacter(player)
          }
        }
      }
    }
  }*/

}
