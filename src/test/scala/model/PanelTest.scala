package cl.uchile.dcc.citric
package model

import model.board.NeutralPanel

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 *
 */
class PanelTest extends munit.FunSuite {
//  private var testNeutralPanel: NeutralPanel = _

  test("A character should end its turn when it falls on a neutral panel") {
    val neutralPanel = new NeutralPanel()
    val character = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11), 5)
    neutralPanel.addCharacter(character)
    neutralPanel.endTurn(character)
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])
  }




}
