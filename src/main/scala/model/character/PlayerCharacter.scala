package cl.uchile.dcc.citric
package model.character

import model.game.TurnSystem
import model.norma._

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
 * @param _name The name of the player. This is an identifier and should be unique.
 * @param _maxHp The maximum health points a player can have. It represents the player's endurance.
 * @param _currentHP The current health points of the player. Default value is the maximum health points.
 * @param _attack The player's capability to deal damage to opponents.
 * @param _defense The player's capability to resist or mitigate damage from opponents.
 * @param _evasion The player's skill to completely avoid certain attacks.
 * @param _randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random` instance.
 * @param _stateKO The state of the player. Default value is false.
 * @param _stars The stars of the player. Default value is 0.
 * @param _victories The victories of the player. Default value is 0.
 *
 *
 * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
 * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
 * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
 * @author [[https://github.com/Seivier/ Vicente González B.]]
 * @author [[https://github.com/fercontreras10/ Fernanda Contreras C.]]
 */
class PlayerCharacter(private val _name: String,
                      private val _maxHp: Int,
                      private var _currentHP: Int,
                      private val _attack: Int,
                      private val _defense: Int,
                      private val _evasion: Int,
                      private val _randomNumberGenerator: Random = new Random(),
                      private var _stateKO: Boolean = false,
                      private var _stars: Int = 0,
                      private var _victories: Int = 0,
                      private var _normaLevel: NormaLevel = new Norma1,
                      private var _starsObjective: Boolean = false,
                      private var _victoriesObjective: Boolean = false,
                      private var _normaCheck: Boolean = false,
                      private var _turnSystem: TurnSystem) extends AbstractEntity {

  /** Sets the turn system of the player.
   *
   * @param newTurnSystem the new turn system of the player.
   */
  def setTurnSystem(newTurnSystem: TurnSystem): Unit = {
    _turnSystem = newTurnSystem
  }

  /** Returns the current chapter of the player. */
  def chapters: Int = _turnSystem.chapters

  /** Returns the unique name of the player. */
  def name: String = _name

  /** Returns the maximum hit points of the player. */
  def maxHp: Int = _maxHp

  /** Returns the current hit points of the player. */
  def currentHp: Int = _currentHP
  /** Updates the current hit points of the player
   *
   * If the new value is less than 0, the current hit points are set to 0.
   *
   * @param newCurrentHp the new current hit points of the player.
   */
  def currentHp_=(newCurrentHp: Int): Unit = {
    _currentHP = Math.max(0, newCurrentHp)
  }

  /** Returns the attack of the player. */
  def attack: Int = _attack

  /** Returns the defense of the player. */
  def defense: Int = _defense

  /** Returns the evasion of the player. */
  def evasion: Int = _evasion

  /** Rolls a dice and returns a value between 1 to 6. */
  override def rollDice(): Int = {
    _randomNumberGenerator.nextInt(6) + 1
  }

  /** Returns the state of the player. */
  def stateKO: Boolean = _stateKO
  /** KnockOut is a method that is called when a player's hit points are 0.
   *
   * If the player's current hit points reach 0, this function sets the KO state of the player to true.
   */
  def knockOut(): Unit = {
    if (_currentHP == 0) {
      _stateKO = true
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
    if(_stateKO) {
      val diceResult = rollDice()
      val recovery = 6 - chapters
      if (diceResult >= recovery) {
        _stateKO = false
        _currentHP = maxHp
      }
    }
  }

  /** Returns the stars of the player. */
  def stars: Int = _stars
  /** Updates the stars of the player.
   *
   * If the new value is less than 0, the stars are set to 0.
   *
   * @param newStars the new stars of the player.
   */
  def stars_=(newStars: Int): Unit = {
    _stars = Math.max(0, newStars)
  }

  /** Returns the victories of the player. */
  def victories: Int = _victories
  /** Updates the victories of the player.
   *
   * If the new value is less than 0, the victories are set to 0.
   *
   * @param newVictories the new victories of the player.
   */
  def victories_=(newVictories: Int): Unit = {
    _victories = Math.max(0, newVictories)  // TODO: seems like this is not needed, cause victories are never decreased
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

  /** Returns the current norma level of the player. */
  def normaLevel: NormaLevel = _normaLevel

  /** Returns the stars objective of the player. */
  def starsObjective: Boolean = _starsObjective

  /** Returns the victories objective of the player. */
  def victoriesObjective: Boolean = _victoriesObjective

  /** Choose stars as the objective */
  private[model] def chooseStarsObjective(): Unit = {
    _starsObjective = true
    _victoriesObjective = false
  }

  /** Choose victories as the objective */
  private[model] def chooseVictoriesObjective(): Unit = {
    _starsObjective = false
    _victoriesObjective = true
  }

  /** Returns true if the player has completed the norma objective, false otherwise.
   */
  def hasCompletedNormaObjective: Boolean = {
    _normaCheck = false
    if (_starsObjective) {
      val requiredStars = _normaLevel.level match {
        case 1 => Norma2().stars
        case 2 => Norma3().stars
        case 3 => Norma4().stars
        case 4 => Norma5().stars
        case 5 => Norma6().stars
      }
      stars >= requiredStars
    }
    else if (_victoriesObjective) {
      val requiredVictories = _normaLevel.level match {
        case 1 => Norma2().victories
        case 2 => Norma3().victories
        case 3 => Norma4().victories
        case 4 => Norma5().victories
        case 5 => Norma6().victories
      }
      victories >= requiredVictories
    }
    else {
      false
    }
  }

  /** Returns the norma check of the player. */
  def normaCheck: Boolean = _normaCheck

  /** Returns the norma level of the player.
   *
   * If the player has completed the norma objective, the norma level is increased by 1.
   * It can't be skipped.
   */
  def normaCheckLevelUp(): Unit = {
    if (hasCompletedNormaObjective) {
      _normaCheck = true
      val currentLevel = _normaLevel.level
      val nextLevel = currentLevel + 1
      if (nextLevel <= 6) {
        nextLevel match {
          case 2 => _normaLevel = new Norma2
          case 3 => _normaLevel = new Norma3
          case 4 => _normaLevel = new Norma4
          case 5 => _normaLevel = new Norma5
          case 6 => _normaLevel = new Norma6
        }
      }
      _starsObjective = false
      _victoriesObjective = false
    }
    normaLevel
  }

  /** Returns half of the stars of a player when a wild unit wins a combat.
   */
  def wildUnitWinCombat(wildUnit: WildUnit): Unit = {
    val starsToTransfer = math.floor(stars / 2).toInt // Round down and convert to Int
    stars -= starsToTransfer
    wildUnit.stars += starsToTransfer
  }

  /** Returns half of the stars of a wild unit when a player wins a combat.
   */
  def playerWinCombat(player: PlayerCharacter, wildUnit: WildUnit): Unit = {
    val starsToTransfer = math.floor(wildUnit.stars / 2).toInt // Round down and convert to Int
    player.stars += starsToTransfer + wildUnit.baseStarQuantity
    wildUnit.stars -= starsToTransfer
  }

  private def playerWinCombatAgainstPlayer(opponent: PlayerCharacter): Unit = {
    val starsToTransfer = math.floor(opponent.stars / 2).toInt // Round down and convert to Int
    stars += starsToTransfer
  }

  /** A combat can be against a player or a wild unit */
  def combat(opponent: Entity): Unit = {
    opponent match {
      case player: PlayerCharacter => playerCombat(this, player)
      case wildUnit: WildUnit => wildUnitCombat(wildUnit)
    }
  }

  def decideCombat(opponent: Entity, defend: Boolean): Unit = {
    opponent match {
      case player: PlayerCharacter =>
        if (defend) {
          player.defendCombat()
        }
        else {
          player.evadeCombat()
        }
      case wildUnit: WildUnit =>
        if (defend) {
          wildUnit.defendCombat()
        }
        else {
          wildUnit.evadeCombat()
        }
    }
  }

  private def combatRound(player1: PlayerCharacter, player2: PlayerCharacter): Unit = {
    player1.attackCombat()
    player2.defendCombat()
    if (player2.currentHp <= 0) {
      player1.increaseVictories("PlayerCharacter")
      player2.playerWinCombatAgainstPlayer(player1)
    }
  }

  private[model] def playerCombat(player1: PlayerCharacter, player2: PlayerCharacter): Unit = {
    if (!player1.stateKO && !player2.stateKO) {
      combatRound(player1, player2)
      combatRound(player2, player1)
    }
  }


  private def wildUnitCombat(wildUnit: WildUnit): Unit = {
    if (!stateKO) {
      // Roll dice for the player
      val playerDiceRoll = rollDice()
      // Calculate the player's total attack
      val playerTotalAttack = attack + playerDiceRoll

      // Determine if the Wild Unit defends or dodges (you can add this logic)
      val wildUnitDefends = scala.util.Random.nextBoolean()

      if (wildUnitDefends) {
        wildUnit.defendCombat()
      }
      else {
        wildUnit.evadeCombat()
      }

      // Check if the Wild Unit's current HP is reduced to 0
      if (wildUnit.currentHp <= 0) {
        stars += wildUnit.stars
        wildUnit.stars = 0
        increaseVictories("WildUnit")
      }
    }
  }

}
