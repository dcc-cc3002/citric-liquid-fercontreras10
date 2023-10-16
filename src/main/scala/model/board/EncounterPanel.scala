package cl.uchile.dcc.citric
package model.board

import model.character.{Chicken, PlayerCharacter, RoboBall, Seagull, WildUnit}

import scala.collection.mutable.ArrayBuffer

/**
 *  EncounterPanel is not tested cause the combat is not implemented yet.
 */

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
    new Chicken(),
    new RoboBall(),
    new Seagull()
  )

  def addWildUnit(): Unit = {
    val enemy = enemies(scala.util.Random.nextInt(enemies.length))
    visibleWildUnit = Some(enemy)
    println(s"Encountered ${enemy.name}!")
  }

  def getVisibleWildUnit: Option[WildUnit] = visibleWildUnit

  /** The player's character initiates a combat with an aleatory WildUnit.
   *
   */
 /* def initiateCombat(player: PlayerCharacter, enemy: WildUnit): Unit = {
    println(s"visibleWildUnit: $visibleWildUnit")
    println(s"enemy: $enemy")
    visibleWildUnit match {
      case Some(visibleEnemy) if visibleEnemy.name == enemy.name =>
        println("Matching enemy names.")
        if (enemy.currentHP <= 0) {
          visibleWildUnit = None
          println(s"Defeated ${enemy.name}!")
        }
        else {
          println(s"Player: ${player.name} | HP: ${player.currentHP} | ATK: ${player.attack} | DEF: ${player.defense} | EVA: ${player.evasion}")
          println(s"Enemy: ${enemy.name} | HP: ${enemy.currentHP} | ATK: ${enemy.attack} | DEF: ${enemy.defense} | EVA: ${enemy.evasion}")
          println("Combat started!")
        }
      case None => println("No enemy to combat!")
    }
  }*/


}
