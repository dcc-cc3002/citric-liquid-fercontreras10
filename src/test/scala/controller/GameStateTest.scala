package cl.uchile.dcc.citric
package controller

import controller.states.{Chapter, PreGame}
import model.character.PlayerCharacter
import model.game.TurnSystem
import model.norma.{Norma1, Norma3, Norma4, Norma6}

import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GameStateTest extends munit.FunSuite {

  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name1 = "testPlayer1"
  private val name2 = "testPlayer2"
  private val name3 = "testPlayer3"
  private val name4 = "testPlayer4"
  private val color = Color.web("#C9E4DE")
  private val maxHp = 10
  private val currentHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val stateKO = true
  private val stars = 0
  private val victories = 0
  private val normaLevel1 = new Norma1
  private val normaLevel2 = new Norma6
  private val normaLevel3 = new Norma3
  private val normaLevel4 = new Norma4
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
      normaLevel1,
      starsObjective,
      victoriesObjective,
      normaCheck,
      turnSystem
    )
    player2 = new PlayerCharacter(
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
    player3 = new PlayerCharacter(
      name3,
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
      normaLevel3,
      starsObjective,
      victoriesObjective,
      normaCheck,
      turnSystem
    )
    player4 = new PlayerCharacter(
      name4,
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
      normaLevel4,
      starsObjective,
      victoriesObjective,
      normaCheck,
      turnSystem
    )
    randomNumberGenerator = new Random(11)
    turnSystem = new TurnSystem(ArrayBuffer(player1, player2, player3, player4))
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

  test("PreGame should initialize board and order players") {
    val gameController = new GameController()
    val preGameState = new PreGame(gameController)
    gameController.initializePlayersOrder()
    preGameState.takeAction()
    assert(gameController.state.isInstanceOf[Chapter])
  }

  /*test("Chapter should increment chapter after all players' turns are finalized") {
    val gameController = new GameController()
    gameController.initializePlayersOrder()
    gameController.nextPlayerTurn()
    val chapterState = new Chapter(gameController)
    assert(gameController.state.isInstanceOf[Chapter])
    assert(gameController.state.chapter == 2)
  }*/

  /*test("Recovery should recover the player and transition to PlayerTurn state") {
    // Create a GameController instance
    val gameController = new GameController()

    // Create a Recovery state instance
    val recoveryState = new Recovery(gameController)

    // Call takeAction on the Recovery state
    recoveryState.takeAction()

    // Assertions
    assertEquals(gameController.currentPlayer.stateKO, false) // Check if the player has recovered
    assert(gameController.state.isInstanceOf[PlayerTurn]) // Check if the state transitions to PlayerTurn
  }*/


}
