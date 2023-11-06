package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

/** The `HomePanel` class represents a panel on the board.
 *
 *  A home panel is a type of panel which is owned by a player character.
 *  Every player has a home panel assigned to them.
 *  When a player lands on their home panel, they recover 1 HP and check if they have completed their norma objective.
 *  To do this, the player can decide if they want to stay in their home panel or not.
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class HomePanel(private val _homePanelOwner: PlayerCharacter) extends AbstractPanel {

  /** Boolean that indicates if the home panel has been activated. */
  private var activate = false

  /** Returns the owner of a Home Panel. */
  def homePanelOwner: PlayerCharacter = _homePanelOwner

  private def activateHomePanel(player: PlayerCharacter): Unit = {
    if (player == homePanelOwner && !activate) {
      stayInHomePanel(player)
      activate = true
    }
  }

  private def stayInHomePanel(player: PlayerCharacter): Unit = {
    player.currentHp += 1
    if (player.hasCompletedNormaObjective) {
      player.normaCheckLevelUp()
    }
    removeCharacter(player)
  }

  /** Applies the effect of the panel to the player that landed on it.
   * For now the decision of starting a combat is made here and is set to true. */
  def apply(player: PlayerCharacter): Unit = {
    activateHomePanel(player)
    activate = false
    /*if (characters.length > 1) {
      startCombat(player, startCombat = true)
    }*/
  }

}
