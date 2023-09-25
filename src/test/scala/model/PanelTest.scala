package cl.uchile.dcc.citric
package model

import model.board.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}
import model.character.{PlayerCharacter, WildUnit}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class PanelTest extends munit.FunSuite {

  test("A character should end its turn when it falls on a neutral panel") {
    val neutralPanel = new NeutralPanel()
    val character = new PlayerCharacter("testPlayer", 10, 10,1, 1, 1, new Random(11), 5)
    neutralPanel.addCharacter(character)
    neutralPanel.endTurn(character)
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  test("A character should win stars when it falls on a bonus panel") {
    val bonusPanel = new BonusPanel()
    val character = new PlayerCharacter("testPlayer", 10, 10, 1, 1, 1, new Random(11), 5)
    bonusPanel.addCharacter(character)
    bonusPanel.increaseStars(character)
    assertEquals(character.stars, 6)
  }

  //test("home panel")

  test("A character should lost stars when it falls on a drop panel") {
    val dropPanel = new DropPanel()
    val character = new PlayerCharacter("testPlayer", 10, 10,1, 1, 1, new Random(11), 5)
    dropPanel.addCharacter(character)
    dropPanel.decreaseStars(character)
    assertEquals(character.stars, 4)
  }

  /*test("A wild unit disappear from an encounter panel when defeated") {
    val encounterPanel = new EncounterPanel()
    val character = new PlayerCharacter("testPlayer", 10, 10,1, 1, 1, new Random(11), 5)
    val enemy = new WildUnit("testWildUnit", 3, 0,-1, -1, +1, 5)
    encounterPanel.addCharacter(character)
    encounterPanel.addWildUnit(enemy)
    encounterPanel.initiateCombat(character, enemy)
    assertEquals(encounterPanel.visibleWildUnit, None)
  }*/

}
