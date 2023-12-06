package cl.uchile.dcc.citric
package model.character

import controller.event.NormaClearEvent
import controller.observer.AbstractSubject

import scala.util.Random

/** The `AbstractEntity` class represents an entity in the game.
 * Implements the common behavior of all entities.
 */
abstract class AbstractEntity extends AbstractSubject[NormaClearEvent] with Entity {

  /** A random number generator used to simulate dice rolls. */
  private val _randomNumberGenerator: Random = new Random()

  /** Represent the dice roll.*/
  def rollDice(): Int = {
    _randomNumberGenerator.nextInt(6) + 1
  }

  /** Represents the attack of the entity. */
  def attackCombat(): Int = {
    this.attack + this.rollDice()
  }

  /** Represents the defense of the entity. */
  def defendCombat(): Int = {
    val damage = calculateDamage()
    applyDamage(damage)
    damage
  }

  /** Represents the evasion of the entity. */
  def evadeCombat(): Int = {
    if (shouldEvade()) {
      0
    }
    else {
      val damage = calculateDamage()
      applyDamage(damage)
      damage
    }
  }

  /** Calculates the damage to apply to the entity.
   *
   * @return the damage to apply to the entity.
   */
  private def calculateDamage(): Int = {
    val roll = rollDice()
    Math.max(0, Math.max(1, roll + this.attack - (roll + defense)))
  }

  /** Applies the damage to the entity.
   *
   * If the damage is greater than the current hit points, the current hit points are set to 0.
   *
   * @param damage the damage to apply to the entity.
   */
  private def applyDamage(damage: Int): Unit = {
    val newCurrentHp = currentHp - damage
    currentHp = Math.max(0, newCurrentHp)
  }

  /** Returns true if the entity should evade the attack, false otherwise. */
  private def shouldEvade(): Boolean = {
    val roll = rollDice()
    roll + evasion <= roll + attack
  }

}
