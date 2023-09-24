package cl.uchile.dcc.citric
package model

import model.board.{EncounterPanel, NeutralPanel}
import model.character.{PlayerCharacter, WildUnit}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 *
 */
class PanelTest extends munit.FunSuite {
//  private var testNeutralPanel: NeutralPanel = _

  test("A character should end its turn when it falls on a neutral panel") {
    val neutralPanel = new NeutralPanel()
    val character = new PlayerCharacter("testPlayer", 10, 10,1, 1, 1, new Random(11), 5)
    neutralPanel.addCharacter(character)
    neutralPanel.endTurn(character)
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }

  /*test("A wild unit disappear from an encounter panel when defeated") {
    val encounterPanel = new EncounterPanel()
    val character = new PlayerCharacter("testPlayer", 10, 10,1, 1, 1, new Random(11), 5)
    val enemy = new WildUnit("testWildUnit", 3, 0,-1, -1, +1, 5)
    encounterPanel.addCharacter(character)
    encounterPanel.showWildUnit(enemy)
    assertEquals(encounterPanel.characters, ArrayBuffer(character))
  }*/

}
