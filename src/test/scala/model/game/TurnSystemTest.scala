package cl.uchile.dcc.citric
package model.game

import model.character.PlayerCharacter
import model.norma.Norma1

import scala.util.Random

class TurnSystemTest extends munit.FunSuite {

  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name1 = "testPlayer1"
  private val name2 = "testPlayer2"
  private val name3 = "testPlayer3"
  private val name4 = "testPlayer4"
  private val maxHp = 10
  private val currentHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val stateKO = false
  private val stars = 0
  private val victories = 0
  private val normaLevel = new Norma1
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
  private var player1: PlayerCharacter = _ // <- x = _ is the same as x = null
  private var player2: PlayerCharacter = _
  private var player3: PlayerCharacter = _
  private var player4: PlayerCharacter = _
  private var turnSystem: TurnSystem = _

  /* Add any other variables you need here... */
  private var randomNumberGenerator = new Random(11)

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new PlayerCharacter(
      name1,
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
    player2 = new PlayerCharacter(
      name2,
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
    player3 = new PlayerCharacter(
      name3,
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
    player4 = new PlayerCharacter(
      name4,
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
    randomNumberGenerator = new Random(11)
    turnSystem = new TurnSystem(List(player1, player2, player3, player4))
  }

  test("A character should have correctly set their attributes") {
    assertEquals(player1.name, name1)
    assertEquals(player1.maxHp, maxHp)
    assertEquals(player1.currentHp, currentHp)
    assertEquals(player1.attack, attack)
    assertEquals(player1.defense, defense)
    assertEquals(player1.evasion, evasion)
    assertEquals(player1.stateKO, stateKO)
    assertEquals(player1.stars, stars)
    assertEquals(player1.victories, victories)

    assertEquals(player2.name, name2)
    assertEquals(player2.maxHp, maxHp)
    assertEquals(player2.currentHp, currentHp)
    assertEquals(player2.attack, attack)
    assertEquals(player2.defense, defense)
    assertEquals(player2.evasion, evasion)
    assertEquals(player2.stateKO, stateKO)
    assertEquals(player2.stars, stars)
    assertEquals(player2.victories, victories)

    assertEquals(player3.name, name3)
    assertEquals(player3.maxHp, maxHp)
    assertEquals(player3.currentHp, currentHp)
    assertEquals(player3.attack, attack)
    assertEquals(player3.defense, defense)
    assertEquals(player3.evasion, evasion)
    assertEquals(player3.stateKO, stateKO)
    assertEquals(player3.stars, stars)
    assertEquals(player3.victories, victories)

    assertEquals(player4.name, name4)
    assertEquals(player4.maxHp, maxHp)
    assertEquals(player4.currentHp, currentHp)
    assertEquals(player4.attack, attack)
    assertEquals(player4.defense, defense)
    assertEquals(player4.evasion, evasion)
    assertEquals(player4.stateKO, stateKO)
    assertEquals(player4.stars, stars)
    assertEquals(player4.victories, victories)
  }

  test("At the start of the game the order of the players is randomized") {
    val initialOrder = turnSystem.playerOrder.map(_.name)
    turnSystem.startGame()
    val randomizedOrder = turnSystem.playerOrder.map(_.name)
    assert(initialOrder != randomizedOrder)
  }

  test("Test advancing to the turn of the next player and chapter progression") {
    // Start the game and simulate a few turns
    //turnSystem.startGame() // omitted to not randomize the order

    // Initially, the current player should be player1
    assertEquals(turnSystem.currentPlayer.name, "testPlayer1")
    assertEquals(turnSystem.chapters, 1)

    // Simulate several turns
    turnSystem.nextPlayerTurn() // Should move to player2
    assertEquals(turnSystem.currentPlayer.name, "testPlayer2")
    assertEquals(turnSystem.chapters, 1)

    turnSystem.nextPlayerTurn() // Should move to player3
    assertEquals(turnSystem.currentPlayer.name, "testPlayer3")
    assertEquals(turnSystem.chapters, 1)

    turnSystem.nextPlayerTurn() // Should move to player4
    assertEquals(turnSystem.currentPlayer.name, "testPlayer4")
    assertEquals(turnSystem.chapters, 1)

    // After player4, it should go back to player1 and advance to a new chapter
    turnSystem.nextPlayerTurn()
    assertEquals(turnSystem.currentPlayer.name, "testPlayer1")
    assertEquals(turnSystem.chapters, 2)
  }

  test("A character wins floor(_chapters / 5) + 1 stars at the start of their turn") {
    // Start the game
    turnSystem.startGame()

    // Check the chapter
    assertEquals(turnSystem.chapters, 1)
    // Advance to a new chapter
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    // Check the stars, floor(1/5) + 1 = 1
    assertEquals(player2.stars, 1)
    assertEquals(player3.stars, 1)
    assertEquals(player4.stars, 1)
    assertEquals(player1.stars, 1)

    // floor(2/5) + 1 = 1
    assertEquals(turnSystem.chapters, 2)
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    assertEquals(player2.stars, 2)
    assertEquals(player3.stars, 2)
    assertEquals(player4.stars, 2)
    assertEquals(player1.stars, 2)

    // floor(3/5) + 1 = 1
    assertEquals(turnSystem.chapters, 3)
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    assertEquals(player2.stars, 3)
    assertEquals(player3.stars, 3)
    assertEquals(player4.stars, 3)
    assertEquals(player1.stars, 3)

    // floor(4/5) + 1 = 1
    assertEquals(turnSystem.chapters, 4)
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    assertEquals(player2.stars, 4)
    assertEquals(player3.stars, 4)
    assertEquals(player4.stars, 4)
    assertEquals(player1.stars, 4)

    // Chapter 5 is special, they get 2 stars, cause floor(5/5) + 1 = 2
    assertEquals(turnSystem.chapters, 5)
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    turnSystem.nextPlayerTurn()
    assertEquals(player2.stars, 6)
    assertEquals(player3.stars, 6)
    assertEquals(player4.stars, 6)
    assertEquals(player1.stars, 6)
  }

}
