package cl.uchile.dcc.citric
package model.board

import model.character.{PlayerCharacter, WildUnit}

import scala.collection.mutable.ArrayBuffer

/** The `EncounterPanel` class represents a panel on the board.
 *  An encounter panel is a type of panel where a character's initiate a combat with an aleatory WildUnit.
 *
 *  @author [[https://github.com/fercontreras10]]
 */
class EncounterPanel() extends Panel {

  /**
   *
   */
  val enemy: ArrayBuffer[WildUnit] = ArrayBuffer()


  /**
   *
   */
  private var visibleWildUnit : Option[WildUnit] = None

  /** An array of the characters currently positioned on this panel.
   *
   */
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()

  /** An array of panels that are directly connected to this one.
   *
   */
  override var nextPanels: ArrayBuffer[Panel] = ArrayBuffer()

  /**
   *
   */
  private val enemies: Array[WildUnit] = Array(
    new WildUnit("Chicken", 3, 3, -1, -1, +1),
    new WildUnit("Robo ball", 3, 3, -1, +1, -1),
    new WildUnit("Seagull", 3, 3, +1, -1, -1)
  )

  def addWildUnit(enemy: WildUnit): Unit = {
    val enemy = enemies(scala.util.Random.nextInt(enemies.length))
    visibleWildUnit = Some(enemy)
    println(s"Encountered ${enemy.name}!")
  }

  private def hideWildUnit(): Unit = {
    visibleWildUnit match {
      case Some(enemy) if enemy.currentHP <= 0 =>
        println(s"Defeated ${enemy.name}!")
        visibleWildUnit = None
      case _ => println("No enemy to combat!")
    }
  }

  /** The player's character initiates a combat with an aleatory WildUnit.
   *
   */
  def initiateCombat(player: PlayerCharacter, enemy: WildUnit): Unit = {
    visibleWildUnit match {
      case Some(enemy) =>
        if (enemy.currentHP <= 0) {
          hideWildUnit()
          println(s"Defeated ${enemy.name}!")
        }
        else {
          println(s"Player: ${player.name} | HP: ${player.currentHP} | ATK: ${player.attack} | DEF: ${player.defense} | EVA: ${player.evasion}")
          println(s"Enemy: ${enemy.name} | HP: ${enemy.currentHP} | ATK: ${enemy.attack} | DEF: ${enemy.defense} | EVA: ${enemy.evasion}")
          println("Combat started!")
        }
      case None => println("No enemy to combat!")
    }
  }


}
