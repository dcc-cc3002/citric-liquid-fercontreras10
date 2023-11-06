package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

/** The `NeutralPanel` class represents a panel on the board.
 *
 *  A neutral panel is a type of panel where a character's turn ends when they land on it.
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class NeutralPanel extends AbstractPanel {

  /** Ends the turn of the character that landed on this panel.
   *
   *  This method is invoked when a character lands on this panel.
   *
   *  @param player The player character that landed on this panel.
   */
  private def endTurn(player: PlayerCharacter): Unit = {
    removeCharacter(player)
  }

  /** Applies the effect of the panel to the player that landed on it.
   * For now the decision of starting a combat is made here and is set to true. */
  def apply(player: PlayerCharacter): Unit = {
    /*if (characters.length > 1) {
      startCombat(player, startCombat = true)
    }*/
    endTurn(player)
  }

}
