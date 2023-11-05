package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

/** The `HomePanel` class represents a panel on the board.
 *
 *  A home panel is a type of panel where a character's
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

  def apply(player: PlayerCharacter): Unit = {
    activateHomePanel(player)
    activate = false
  }

}
