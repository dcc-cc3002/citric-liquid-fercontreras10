package cl.uchile.dcc.citric
package model.board

import model.character.{Chicken, PlayerCharacter, RoboBall, Seagull, WildUnit}

import scala.collection.mutable.ArrayBuffer

/** The `EncounterPanel` class represents a panel on the board.
 *  An encounter panel is a type of panel where a character's initiate a combat with an aleatory WildUnit.
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class EncounterPanel extends AbstractPanel {

  /** The name of the panel.
   *
   * This might be used to display the name of the panel on the board.
   *
   * @return the name of the panel.
   */
  override def name: String = "Encounter Panel"

  /** Array of the WildUnits currently positioned on this panel.
   *
   * In the game, the characters will enter a combat with an aleatory WildUnit.
   */
  private val enemies: ArrayBuffer[WildUnit] = ArrayBuffer(
    new Chicken,
    new RoboBall,
    new Seagull
  )

  /** The player's character initiates a combat with an aleatory WildUnit.
   *
   *  This method is invoked when a character lands on this panel.
   *
   *  @param player The player character that landed on this panel.
   */
  private def initiateCombat(player: PlayerCharacter): Unit = {
    val enemy = enemies(scala.util.Random.nextInt(enemies.length))
    player.combat(enemy)
  }

  /** Applies the effect of the panel to the player that landed on it.
   * For now the decision of starting a combat is made here and is set to true. */
  def apply(player: PlayerCharacter): Unit = {
    initiateCombat(player) // against wild unit
    if (characters.length > 1) {
      startCombat(player, startCombat = true)  // against other players
    }
  }

}
