package cl.uchile.dcc.citric
package model.board

import cl.uchile.dcc.citric.model.character.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** The `HomePanel` class represents a panel on the board.
 *  A home panel is a type of panel where a character's
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class HomePanel extends Panel {

  /** An array of the characters currently positioned on this panel.
   *
   */
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()

  /** An array of panels that are directly connected to this one.
   *
   */
  override var nextPanels: ArrayBuffer[Panel] = ArrayBuffer()

  /** The player's character recovers 1 HP.
   *
   */

}
