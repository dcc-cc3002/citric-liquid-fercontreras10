package cl.uchile.dcc.citric
package model.character

/** Represents an entity in the game.
 *
 * It can be a PlayerCharacter or a WildUnit.
 *
 * @author [[https://github.com/fercontreras10]]
 */
trait Entity {

  /** Returns the unique name of the PlayerCharacter or WildUnit. */
  def name: String

  /** Returns the maximum hit points of the PlayerCharacter or WildUnit. */
  def maxHp: Int

  /** Returns the current hit points of the PlayerCharacter or WildUnit. */
  def currentHp: Int

  /** Updates the current hit points of the PlayerCharacter or WildUnit.
   *
   * If the new value is less than 0, the current hit points are set to 0.
   *
   * @param newCurrentHp the new current hit points of the PlayerCharacter or WildUnit.
   */
  def currentHp_=(newCurrentHp: Int): Unit

  /** Returns the attack of the PlayerCharacter or WildUnit. */
  def attack: Int

  /** Returns the defense of the PlayerCharacter or WildUnit. */
  def defense: Int

  /** Returns the evasion of the PlayerCharacter or WildUnit. */
  def evasion: Int

  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(): Int

  /** Returns the stars of the PlayerCharacter or WildUnit. */
  def stars: Int

  /** Updates the stars of the PlayerCharacter or WildUnit.
   *
   * If the new value is less than 0, the stars are set to 0.
   *
   * @param newStars the new stars of the PlayerCharacter or WildUnit.
   */
  def stars_=(newStars: Int): Unit

  /** Represent an attack actions in a combat. */
  def attackCombat(): Int

  /** Represent the defender's actions in a combat. */
  def defendCombat(): Int

  /** Represent the evader's actions in a combat. */
  def evadeCombat(): Int

}
