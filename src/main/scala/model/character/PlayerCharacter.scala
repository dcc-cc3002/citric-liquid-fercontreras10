package cl.uchile.dcc.citric
package model.character

import model.norma.{Norma, NormaType}

import scala.util.Random

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
 *
  * - Increase or decrease their star count.
 *
  * - Roll a dice, a common action in many board games.
 *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  * @param stars The number of stars the player has obtained. Defaults to 0.
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/fercontreras10/ Fernanda Contreras C.]]
  */
class PlayerCharacter(val name: String,
                      val maxHp: Int,
                      var currentHP: Int,
                      val attack: Int,
                      val defense: Int,
                      val evasion: Int,
                      val randomNumberGenerator: Random = new Random(),
                      var stars: Int = 0,
                      var victories: Int = 0,
                      var stateKO: Boolean = false) {

  var normaLevel: NormaType = Norma.Norma1
  private var _chapters: Int = 1

  private def chapters: Int = _chapters

  def setChapters(value: Int): Unit = {
    _chapters = value
  }

  /** Rolls a dice and returns a value between 1 to 6.
   *
   * @return a random number between 1 and 6.
   */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }

  /** Increases the number of stars.
   *
   * @param amount the amount of stars to increase.
   */
  def increaseStars(amount: Int): Unit = {
    stars += amount
  }

  /** Decreases the number of stars.
   *
   * @param amount the amount of stars to decrease.
   */
  def decreaseStars(amount: Int): Unit = {
    stars -= amount
  }

  /** Victories are increased when a combat is won.
   * The amount increased depends on the opponent defeated.
   * It can be a WildUnit or another PlayerCharacter.
   * A WildUnit increases the victories by 1.
   * A PlayerCharacter increases the victories by 2.
   *
   * @param opponentType the amount of victories to increase.
   */
  def increaseVictories(opponentType: String): Unit = {
    opponentType match {
      case "WildUnit" => victories += 1
      case "PlayerCharacter" => victories += 2
    }
  }

  /** Recovers the player's health points.
   *
   * Used in HomePanel when it activates.
   *
   * @param hp the amount of health points to recover.
   */
  def recoverHP(hp: Int): Unit = {
    currentHP += hp
  }

  /** KO is a method that is called when a player's health points are 0 or less.
   * It changes the state of the player to KO and calls the recovery method.
   */
  def knockOut(): Unit = {
    if (currentHP <= 0) {// to check: or just == 0?
      stateKO = true
      recovery()
    }
  }

  /** Recovery is a method that is called when a player is in KO state.
   * It rolls a dice and if the result is greater than or equal to 6 - chapters,
   * the player recovers.
   *
   * It's missing the implementation of turns.
   */
  def recovery(): Unit = {
    if(stateKO) {
      val diceResult = rollDice()
      val recovery = 6 - chapters
      if (diceResult >= recovery) {
        stateKO = false
        currentHP = maxHp
      }
    }
  }
}
