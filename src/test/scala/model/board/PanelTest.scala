package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter
import model.game.TurnSystem
import model.norma.Norma1

import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class PanelTest extends munit.FunSuite {

  // ask how to avoid duplication in this case, creating a fake character object or something like that
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name1 = "testPlayer"
  private val name2 = "testPlayer2"
  private val color = Color.web("#C9E4DE")
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
  private var character: PlayerCharacter = _ // <- x = _ is the same as x = null
  private var otherCharacter: PlayerCharacter = _
  private var neutralPanel: NeutralPanel = _
  private var homePanel: HomePanel = _
  private var bonusPanel: BonusPanel = _
  private var dropPanel: DropPanel = _
  private var encounterPanel: EncounterPanel = _
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
      normaLevel,
      starsObjective,
      victoriesObjective,
      normaCheck,
      turnSystem
    )
    randomNumberGenerator = new Random(11)
    neutralPanel = new NeutralPanel()
    homePanel = new HomePanel(character)
    bonusPanel = new BonusPanel()
    dropPanel = new DropPanel()
    encounterPanel = new EncounterPanel()
    turnSystem = new TurnSystem(ArrayBuffer[PlayerCharacter](character, otherCharacter))
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.name, name1)
    assertEquals(otherCharacter.name, name2)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.currentHp, currentHp)
    assertEquals(character.attack, attack)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
    assertEquals(character.stars, stars)
    assertEquals(character.victories, victories)
  }

  //----------------------------------------------------------------------------------

  //private var neutralPanel: NeutralPanel = _
  //private var homePanel: HomePanel = _
  //private var bonusPanel: BonusPanel = _
  //private var dropPanel: DropPanel = _
  //private var encounterPanel: EncounterPanel = _

  /*override def beforeEach(context: BeforeEach): Unit = {
    neutralPanel = new NeutralPanel()
    homePanel = new HomePanel()
    bonusPanel = new BonusPanel()
    dropPanel = new DropPanel()
    encounterPanel = new EncounterPanel()
  }*/

  test("A character can be added to a panel") {
    neutralPanel.addCharacter(character)
    assertEquals(neutralPanel.characters, ArrayBuffer(character))
  }

  test("A character can be removed from a panel") {
    neutralPanel.addCharacter(character)
    neutralPanel.removeCharacter(character)
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("A panel can be added next to another panel") {
    val nextPanel = new NeutralPanel()
    neutralPanel.addNextPanel(nextPanel)
    assertEquals(neutralPanel.nextPanels, ArrayBuffer[Panel](nextPanel))
  }

  test("A panel can be removed next to another panel") {
    val nextPanel = new NeutralPanel()
    neutralPanel.addNextPanel(nextPanel)
    neutralPanel.removeNextPanel(nextPanel)
    assertEquals(neutralPanel.nextPanels, ArrayBuffer.empty[Panel])
  }

  test("A character should end its turn when it falls on a neutral panel") {
    neutralPanel.addCharacter(character)
    neutralPanel.apply(character)
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  /*test("A character can start a combat with another character in a neutral panel") {
    neutralPanel.addCharacter(character)
    neutralPanel.addCharacter(otherCharacter)
    neutralPanel.apply(character)
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])

  }*/

  test("A character is the owner of a home panel at the beginning of the game") {
    homePanel.addCharacter(character)
    assertEquals(homePanel.homePanelOwner, character)
  }

  test("A character can decide to stay in a home panel even if it has available movements") {
    homePanel.addCharacter(character)
    character.stars = 10
    character.chooseStarsObjective()
    homePanel.apply(character)
    assertEquals(homePanel.homePanelOwner, character)
    assertEquals(character.currentHp, 11)
    assertEquals(character.normaCheck, true)
    assertEquals(homePanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("A character should win stars when it falls on a bonus panel") {
    character.stars = 4
    bonusPanel.addCharacter(character)
    bonusPanel.apply(character)
    assertEquals(character.stars, 5)
  }

  test("A character should lost stars when it falls on a drop panel") {
    character.stars = 4
    dropPanel.addCharacter(character)
    dropPanel.apply(character)
    assertEquals(character.stars, 3)
  }

  test("A character should enter a combat with an aleatory enemy when it falls on a encounter panel") {
    encounterPanel.addCharacter(character)
    encounterPanel.apply(character)
  }

  test("startCombat initiates combat when conditions are met") {
    val panel = new NeutralPanel
    panel.addCharacter(character)
    val opponent = otherCharacter
    panel.addCharacter(opponent)
    panel.startCombat(character, startCombat = true)
    assert(panel.characters.contains(opponent))
  }

  test("startCombat does not initiate combat when conditions are not met") {
    val panel = new NeutralPanel
    panel.addCharacter(character)
    panel.startCombat(character, startCombat = false)
    assertEquals(panel.characters.length, 1)
  }

  test("Correct return of names") {
    assertEquals(neutralPanel.name, "Neutral Panel")
    assertEquals(homePanel.name, "Home Panel")
    assertEquals(bonusPanel.name, "Bonus Panel")
    assertEquals(dropPanel.name, "Drop Panel")
    assertEquals(encounterPanel.name, "Encounter Panel")
  }

  test("Board should draw correctly") {
    val board = new Board

    val expectedOutput =
      """a1 b1 c1             h1 i1 j1
        |a2    c2 d2 e2 f2 g2 h2    j2
        |a3 b2 c3             h3 i2 j3
        |""".stripMargin

    val printedOutput = new java.io.ByteArrayOutputStream() // Redirect stdout to capture printed output
    Console.withOut(printedOutput) {
      board.drawBoard()
    }

    val normalizedExpectedOutput = expectedOutput.stripMargin.replaceAll("\\s", "")
    val normalizedActualOutput = printedOutput.toString.stripMargin.replaceAll("\\s", "")

    assert(normalizedActualOutput == normalizedExpectedOutput)
  }

}
