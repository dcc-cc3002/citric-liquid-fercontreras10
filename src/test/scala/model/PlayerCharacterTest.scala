package cl.uchile.dcc.citric
package model

import model.character.PlayerCharacter

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name = "testPlayer"
  private val maxHp = 10
  private val currentHP = 0
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val victories = 0
  /* Add any other constants you need here... */

  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */
  private var randomNumberGenerator = new Random(11)

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    character = new PlayerCharacter(
      name,
      maxHp,
      currentHP,
      attack,
      defense,
      evasion,
      randomNumberGenerator,
      victories
    )
    randomNumberGenerator = new Random(11)
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.name, name)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.currentHP, currentHP)
    assertEquals(character.attack, attack)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
    //assertEquals(character.stars, stars)
    assertEquals(character.victories, victories)
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
    val other = {
      new PlayerCharacter(name, maxHp, currentHP, attack, defense, evasion, new Random(11))
    }
    for (_ <- 1 to 10) {
      assertEquals(character.rollDice(), other.rollDice())
    }
  }

  test("A character should be able to increased their stars counter") {
    character.stars += 3
    assertEquals(character.stars, 3)
  }

  test("A character should be able to decreased their stars counter") {
    character.stars -= 3
    assertEquals(character.stars, 0)
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

  test("A character recover HP") {
    character.recoverHP(2)
    assertEquals(character.currentHP, 2)
  }

  test("A character enters a KO state when their HP is 0 or less") {
    character.knockOut()
    assertEquals(character.stateKO, true)
  }

  test("A character in KO state enters a Recovery state") {
    character.recovery()
    assertEquals(character.stateKO, false)
  }

  test("A character in KO state does not recover if the dice result is less than 6 - chapters") {
    character.knockOut()
    character.recovery()
    assertEquals(character.currentHP, 0)
  }

  test("A character in KO state recovers if the dice result is greater than or equal to 6 - chapters") {
    character.knockOut()
    character.chapters_(5)
    character.recovery()
    assertEquals(character.currentHP, maxHp)
  }

}
