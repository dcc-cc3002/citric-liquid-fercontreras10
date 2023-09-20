package cl.uchile.dcc.citric
package model.board

import model.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** The `NeutralPanel` class represents a panel on the board.
 *  A neutral panel is a type of panel where a character's turn ends when they land on it.
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class NeutralPanel() extends Panel {

  /** An array of the characters currently positioned on this panel.
   *
   */
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()

  /** An array of panels that are directly connected to this one.
   *
   */
  override var nextPanels: ArrayBuffer[Panel] = ArrayBuffer()

  /** Ends the turn of the character that landed on this panel.
   *
   * This method is invoked when a character lands on this panel.
   *
   * @param player The player character that landed on this panel.
   */
  def endTurn(player: PlayerCharacter): Unit = {
    removeCharacter(player)
  }
}

