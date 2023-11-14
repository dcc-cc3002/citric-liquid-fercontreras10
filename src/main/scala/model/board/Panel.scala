package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** Represents a single cell on a board, known as a Panel.
 *
 * Each panel has its own effect, which can be applied to a character.
 * In the context of the board game, a panel represents a tile or space that a character lands on
 * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
 * Panels can also be connected to other panels, allowing for the formation of complex board
 * structures.
 *
 * @author [[https://github.com/r8vnhill Ignacio Slater M.]]
 * @author [[https://github.com/fercontreras10]]
 */
trait Panel {

  /** Array of the characters currently positioned on this panel.
   *
   * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
   * land on the same space.
   */
  val characters: ArrayBuffer[PlayerCharacter]

  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  var nextPanels: ArrayBuffer[Panel]

  /** Adds a character to the list of characters currently on this panel.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param player The player character to add to this panel.
   */
  def addCharacter(player: PlayerCharacter): Unit

  /** Removes a character from the list of characters currently on this panel.
   *
   * This might be invoked when a player moves off this panel.
   *
   * @param player The player character to remove from this panel.
   */
  def removeCharacter(player: PlayerCharacter): Unit

  /** Adds a panel to the list of panels that are directly connected to this one.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param panel The panel to add to this panel.
   */
  def addNextPanel(panel: Panel): Unit

  /** Removes a panel from the list of panels that are directly connected to this one.
   *
   * This might be invoked when a player moves off this panel.
   *
   * @param panel The panel to remove from this panel.
   */
  def removeNextPanel(panel: Panel): Unit

  /** Applies the effect of this panel to a character.
   *
   * This method is invoked when a character lands on this panel.
   *
   * @param player The player character to apply the effect to.
   */
  def apply(player: PlayerCharacter): Unit

  /** A player can decide to initiate a combat when there's other player in a panel.
   *
   * This method is invoked when a characters lands on this panel and there's another player in it.
   * The combat is initiated by the player that lands on the panel
   * and is fought against a random player in the panel.
   *
   * //@param player The player character that initiates the combat.
   */
  def startCombat(player: PlayerCharacter, startCombat: Boolean): Unit
}
