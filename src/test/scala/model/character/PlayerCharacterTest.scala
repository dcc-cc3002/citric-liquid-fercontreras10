package cl.uchile.dcc.citric
package model.character

import model.board.HomePanel
import model.game.TurnSystem
import model.norma._

import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name1 = "testPlayer"
  private val name2 = "testPlayer2"
  private val color = Color.web("#C9E4DE")
  private val maxHp = 10
  private val currentHp = 0
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val stateKO = false
  private val stars = 0
  private val victories = 0
  private val normaLevel = new Norma1
  private val normaLevel2 = new Norma6
  private val starsObjective = false
  private val victoriesObjective = false
  private val normaCheck = false

  /* Add any other constants you need here... */

  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null
  private var otherCharacter: PlayerCharacter = _
  private var turnSystem: TurnSystem = _
  /* Add any other variables you need here... */
  private var randomNumberGenerator = new Random(11)

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    character = new PlayerCharacter(
      name1,
      color,
      maxHp,
      currentHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator,
      stateKO,
      stars,
      victories,
      normaLevel,
      starsObjective,
      victoriesObjective,
      normaCheck,
      turnSystem
    )
    otherCharacter = new PlayerCharacter(
      name2,
      color,
      maxHp,
      currentHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator,
      stateKO,
      stars,
      victories,
      normaLevel2,
      starsObjective,
      victoriesObjective,
      normaCheck,
      turnSystem
    )
    randomNumberGenerator = new Random(11)
    turnSystem = new TurnSystem(ArrayBuffer[PlayerCharacter](character))
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.name, name1)
    assertEquals(otherCharacter.name, name2)
    assertEquals(character.color, color)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.currentHp, currentHp)
    assertEquals(character.attack, attack)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
    assertEquals(character.stateKO, stateKO)
    assertEquals(character.stars, stars)
    assertEquals(character.victories, victories)
    assertEquals(character.normaLevel, normaLevel)
  }

  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice >= 1 && character.rollDice <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A character should be able to roll a dice with a fixed seed") {
    val other =
      new PlayerCharacter(name1, color, maxHp, currentHp, attack, defense, evasion, new Random(11), stateKO, stars, victories, normaLevel, starsObjective, victoriesObjective, normaCheck, turnSystem)
    for (_ <- 1 to 10) {
      assertEquals(character.rollDice(), other.rollDice())
    }
  }

  test("The characters should have unique names") {
    val character2 = new PlayerCharacter("testPlayer2", color, maxHp, currentHp, attack, defense, evasion, randomNumberGenerator, stateKO, stars, victories, normaLevel, starsObjective, victoriesObjective, normaCheck, turnSystem)
    assert(!character2.name.equals(character.name))
  }

  test("A character's currentHP should be less than or equal to their maxHP") {
    assert(character.currentHp <= character.maxHp)
  }

  test("A character's currentHP can't be less than 0") {
    character.currentHp = -3
    assertEquals(character.currentHp, 0)
  }

  test("A character should enter KO state when their currentHP is 0") {
    character.currentHp = 0
    character.knockOut()
    assertEquals(character.stateKO, true)
  }

  test("A character in KO state enters a Recovery state") {
    character.recovery()
    assertEquals(character.stateKO, false)
  }

  test("Unsuccessful recovery should not restore health points") {
    character.setTurnSystem(new TurnSystem(ArrayBuffer(character)))
    character.knockOut()
    character.recovery()
    assertEquals(character.stateKO, true)
    assertEquals(character.currentHp, 0)
  }

  test("Successful recovery should restore health points") {
    val turnSystem = new TurnSystem(ArrayBuffer(character))
    character.setTurnSystem(turnSystem)
    character.knockOut()
    // check chapter
    assertEquals(turnSystem.chapters, 1)
    // advanced next turn to get diceResult >= recovery
    turnSystem.nextPlayerTurn()
    assertEquals(turnSystem.chapters, 2)
    turnSystem.nextPlayerTurn()
    assertEquals(turnSystem.chapters, 3)
    character.recovery()
    assertEquals(character.stateKO, false)
    assertEquals(character.currentHp, character.maxHp)
  }

  test("A character should have 0 stars at the beginning of the game") {
    assertEquals(character.stars, 0)
  }

  test("A character stars can't be less than 0") {
    character.stars = -3
    assertEquals(character.stars, 0)
  }

  test("A character should be able to increased their stars counter through the game") {
    character.stars += 3
    assertEquals(character.stars, 3)
  }

  test("A character can lose stars through the game, so it should be able to decreased their stars counter") {
    character.stars -= 3
    assertEquals(character.stars, 0)
  }

  test("A character should have 0 victories at the beginning of the game") {
    assertEquals(character.victories, 0)
  }

  test("A character victories can't be less than 0") {
    character.victories = -1
    assertEquals(character.victories, 0)
  }

  test("A character should be able to increased their victories counter through the game") {
    character.victories += 1
    assertEquals(character.victories, 1)
  }

  test("A character should be able to increased their victories counter to 1 if the opponent is a WildUnit") {
    val opponentType = "WildUnit"
    character.increaseVictories(opponentType)
    assertEquals(character.victories, 1)
  }

  test("A character should be able to increased their victories counter to 2 if the opponent is a PlayerCharacter") {
    val opponentType = "PlayerCharacter"
    character.increaseVictories(opponentType)
    assertEquals(character.victories, 2)
  }

  test("A character can choose stars as the objective to complete") {
    character.chooseStarsObjective()
    assertEquals(character.starsObjective, true)
  }

  test("A character can choose victories as the objective to complete") {
    character.chooseVictoriesObjective()
    assertEquals(character.victoriesObjective, true)
   }

  test("A character can choose only one objective to complete at a time per norma level") {
    val homePanel = new HomePanel(character)

    character.chooseStarsObjective() // Set stars objective
    homePanel.addCharacter(character)
    character.stars = Norma2().stars // Assume the character has enough stars for Norma2
    assertEquals(character.starsObjective, true)
    assertEquals(character.victoriesObjective, false)
    homePanel.apply(character)


    character.chooseVictoriesObjective() // Set victories objective
    homePanel.addCharacter(character)
    character.victories = Norma3().victories // Now, assume the character has enough victories for Norma3
    assertEquals(character.starsObjective, false)
    assertEquals(character.victoriesObjective, true)
    homePanel.apply(character)
  }

  test("A character can level up the Norma choosing stars as the objective") {
    val homePanel = new HomePanel(character)

    homePanel.addCharacter(character)
    character.stars = 10 // Sufficient stars for Norma2
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma2)

    homePanel.addCharacter(character)
    character.stars = 30 // Sufficient stars for Norma3
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma3)

    homePanel.addCharacter(character)
    character.stars = 70 // Sufficient stars for Norma4
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma4)

    homePanel.addCharacter(character)
    character.stars = 120 // Sufficient stars for Norma5
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma5)

    homePanel.addCharacter(character)
    character.stars = 200 // Sufficient stars for Norma6
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma6)
  }

  test("A character can level up the Norma choosing victories as the objective") {
    val homePanel = new HomePanel(character)

    homePanel.addCharacter(character)
    character.victories = 1  // Sufficient victories for Norma2
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma2)

    homePanel.addCharacter(character)
    character.victories = 3 // Sufficient victories for Norma3
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma3)

    homePanel.addCharacter(character)
    character.victories = 6 // Sufficient victories for Norma4
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma4)

    homePanel.addCharacter(character)
    character.victories = 10 // Sufficient victories for Norma5
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma5)

    homePanel.addCharacter(character)
    character.victories = 14 // Sufficient victories for Norma6
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma6)
  }

  test("A character can level up the Norma choosing a mix of stars and victories as the objective") {
    val homePanel = new HomePanel(character)

    homePanel.addCharacter(character)
    character.stars = 10 // Sufficient stars for Norma2
    character.victories = 1 // Sufficient victories for Norma2
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma2)

    homePanel.addCharacter(character)
    character.stars = 30 // Sufficient stars for Norma3
    character.victories = 3 // Sufficient victories for Norma3
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma3)

    homePanel.addCharacter(character)
    character.stars = 70 // Sufficient stars for Norma4
    character.victories = 6 // Sufficient victories for Norma4
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma4)

    homePanel.addCharacter(character)
    character.stars = 120 // Sufficient stars for Norma5
    character.victories = 10 // Sufficient victories for Norma5
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma5)

    homePanel.addCharacter(character)
    character.stars = 200 // Sufficient stars for Norma6
    character.victories = 14 // Sufficient victories for Norma6
    character.chooseVictoriesObjective()
    homePanel.apply(character)
    assertEquals(character.normaLevel, new Norma6)
  }

  test("A character could not have met the stars objective") {
    val homePanel = new HomePanel(character)

    homePanel.addCharacter(character)
    character.stars = 9 // No sufficient stars for Norma2
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(character.hasCompletedNormaObjective, false)
    assertEquals(character.normaLevel, new Norma1)
  }

  test("hasCompletedNormaObjective should return false when no objective is met") {
    assertEquals(character.hasCompletedNormaObjective, false)
  }

  test("A character should be able to calculate their attack") {
    assertEquals(character.attackCombat(), 2)
  }

  test("defendCombat should calculate damage and apply it to currentHp") {
    assertEquals(character.currentHp, 0)
    assertEquals(character.defendCombat(), 1)
  }

  test("evadeCombat should calculate damage, apply it if not evading, and return damage") {
    assertEquals(character.currentHp, 0)
    assertEquals(character.evadeCombat(), 0)
  }

  test("When a wild unit wins a combat, the player should transfer half of their stars") {
    character.stars = 10
    val wildUnit = new Chicken
    character.wildUnitWinCombat(wildUnit)
    assertEquals(character.stars, 5)
  }

  test("When a player wins a combat, the wild unit should transfer half of their stars + their bono stars") {
    character.stars = 10
    val wildUnit = new Chicken
    character.playerWinCombat(character, wildUnit)
    assertEquals(character.stars, 14)
    assertEquals(wildUnit.stars, 3)
  }

  test("Player should transfer half of opponent's stars after winning combat against another player") {
    otherCharacter.stars = 10
    character.playerWinCombatAgainstPlayer(otherCharacter)
    assertEquals(character.stars, 5)
  }

  test("Player can decide to combat PlayerCharacter or WildUnit") {
    character.decideCombat(otherCharacter, defend = true)
    assertEquals(character.currentHp, 0)
    val wildUnitOpponent = new Chicken
    character.decideCombat(wildUnitOpponent, defend = false)
    assertEquals(character.currentHp, 0)
  }

  test("Player evading should not reduce opponent's attack") {
    val opponent = new Chicken
    val initialOpponentAttack = opponent.attack
    character.decideCombat(opponent, defend = false)
    assertEquals(opponent.attack, initialOpponentAttack)
  }

  test("Player combat against another PlayerCharacter") {
    character.playerCombat(character, otherCharacter)
    assertEquals(2, character.victories)
    assertEquals(2, otherCharacter.victories)
    assertEquals(0, character.stars)
    assertEquals(0, otherCharacter.stars)
  }

  test("Player combat against another PlayerCharacter with victories") {
    otherCharacter.currentHp = 1
    character.playerCombat(character, otherCharacter)
    assertEquals(2, character.victories)
    assertEquals(2, otherCharacter.victories)
    assertEquals(0, character.stars)
    assertEquals(0, otherCharacter.stars)
  }

  test("hasReachedNorma6 should return true when _normaLevel is 6") {
    assertEquals(otherCharacter.hasReachedNorma6, true)
  }

  test("hasReachedNorma6 should return false when _normaLevel is not 6") {
    assertEquals(character.hasReachedNorma6, false)
  }
}
