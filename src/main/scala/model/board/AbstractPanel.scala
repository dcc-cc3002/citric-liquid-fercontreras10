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

  /*def initiateCombat(player: PlayerCharacter): Unit = {
    if (characters.nonEmpty) {
      val playerChoice = "yes"
      if (playerChoice == "yes") {
        val opponent = characters.head
        player.playerCombat(opponent, this)
        if (opponent.stateKO) {
          removeCharacter(opponent)
        }
      }
      else {
        //just apply panel effect
      }
    }
    else {
      // do not call initiate combat cause there is no one to fight, so create function outside
    }
  }*/

}
