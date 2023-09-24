package cl.uchile.dcc.citric
package model

import model.character.WildUnit

class WildUnitTest extends munit.FunSuite {

  private val name = "Chicken"
  private val maxHp = 3
  private val currentHP = 0
  private val attack = -1
  private val defense = -1
  private val evasion = +1
  private val stars = 5

  private var enemy: WildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    enemy = new WildUnit(
      name,
      maxHp,
      currentHP,
      attack,
      defense,
      evasion,
      stars
    )
  }

  test("A WildUnit should have correctly set their attributes") {
    assertEquals(enemy.name, name)
    assertEquals(enemy.maxHp, maxHp)
    assertEquals(enemy.currentHP, currentHP)
    assertEquals(enemy.attack, attack)
    assertEquals(enemy.defense, defense)
    assertEquals(enemy.evasion, evasion)
    assertEquals(enemy.stars, stars)
  }

  test("A WildUnit should be able to increased their stars counter") {
    enemy.increaseStars(5)
    assertEquals(enemy.stars, 10)
  }






}
