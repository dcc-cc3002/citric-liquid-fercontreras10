package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** The `DropPanel` class represents a panel on the board.
 *  A drop panel is a type of panel where a character's lost stars when they land on it.
 *  To know how many stars are lost the player needs to roll the dice so the calculus is
 *  number of dice * Norma of player.
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class DropPanel extends Panel {

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

  /** Decreases the number of stars of the character that landed on this panel.
   *
   * This method is invoked when a character lands on this panel.
   *
   * @param player the player that landed on the panel.
   */
  def decreaseStars(player: PlayerCharacter): Unit = {
    val diceRoll = player.rollDice()
    player.stars -= (diceRoll * player.normaLevel.normaId)
  }
}
