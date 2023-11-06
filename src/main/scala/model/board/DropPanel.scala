package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

/** The `DropPanel` class represents a panel on the board.
 *  A drop panel is a type of panel where a character's lost stars when they land on it.
 *  To know how many stars are lost the player needs to roll the dice so the calculus is
 *  number of dice * Norma of player.
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class DropPanel extends AbstractPanel {

  /** Decreases the number of stars of the character that landed on this panel.
   *
   * This method is invoked when a character lands on this panel.
   *
   * @param player the player that landed on the panel.
   */
  private def decreaseStars(player: PlayerCharacter): Unit = {
    val diceRoll = player.rollDice()
    player.stars -= diceRoll * player.normaLevel.level
  }

  /** Applies the effect of the panel to the player that landed on it.
   * For now the decision of starting a combat is made here and is set to true. */
  def apply(player: PlayerCharacter): Unit = {
    decreaseStars(player)
    /*if (characters.length > 1) {
      startCombat(player, startCombat = true)
    }*/
  }

}
