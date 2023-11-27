package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

/** The `BonusPanel` class represents a panel on the board.
 *  A bonus panel is a type of panel where a character's wins stars when they land on it.
 *  To know how many stars are win the player needs to roll the dice so the calculus is
 *  min(number of dice * Norma of player, number of dice * 3).
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class BonusPanel extends AbstractPanel {

  /** Increases the number of stars of the character that landed on this panel.
   *
   * This method is invoked when a character lands on this panel.
   *
   * @param player the player that landed on the panel.
   */
  private def increaseStars(player: PlayerCharacter): Unit = {
    val diceRoll = player.rollDice()
    player.stars += Math.min(diceRoll * player.normaLevel.level, diceRoll * 3)
  }

  /** Applies the effect of the panel to the player that landed on it.
   * For now the decision of starting a combat is made here and is set to true. */
  def apply(player: PlayerCharacter): Unit = {
    increaseStars(player)
    if (characters.length > 1) {
      startCombat(player, startCombat = true)
    }
  }
}
