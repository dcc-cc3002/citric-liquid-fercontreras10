package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.math._

/** The `BonusPanel` class represents a panel on the board.
 *  A bonus panel is a type of panel where a character's wins stars when they land on it.
 *  To know how many stars are win the player needs to roll the dice so the calculus is
 *  min(number of dice * Norma of player, number of dice * 3).
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class BonusPanel extends Panel {

  /** Array of the characters currently positioned on this panel.
   *
   * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
   * land on the same space.
   */
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()

  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  override var nextPanels: ArrayBuffer[Panel] = ArrayBuffer()

  /** Increases the number of stars of the character that landed on this panel.
   *
   * This method is invoked when a character lands on this panel.
   *
   * @param player the player that landed on the panel.
  */
  def increaseStars(player: PlayerCharacter): Unit = {
    val diceRoll = player.rollDice()
    player.stars += min(diceRoll * player.normaLevel.normaId, diceRoll * 3)
  }
}
