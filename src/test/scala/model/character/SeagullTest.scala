package cl.uchile.dcc.citric
package model.character

class SeagullTest extends munit.FunSuite {

  private val name = "Seagull"
  private val maxHp = 3
  private val currentHp = 3
  private val attack = +1
  private val defense = -1
  private val evasion = -1
  private val stateVisible = false
  private val stars = 2

  private var enemy: WildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    enemy = new Seagull
  }

  test("A Seagull wild unit should have correctly set their attributes") {
    assertEquals(enemy.name, name)
    assertEquals(enemy.maxHp, maxHp)
    assertEquals(enemy.currentHp, currentHp)
    assertEquals(enemy.attack, attack)
    assertEquals(enemy.defense, defense)
    assertEquals(enemy.evasion, evasion)
    assertEquals(enemy.stateVisible, stateVisible)
    assertEquals(enemy.stars, stars)
  }

  test("A Seagull wild unit should have 2 stars at the beginning of the game") {
    assertEquals(enemy.stars, 2)
  }

  test("A Seagull wild unit stars can't be less than 2") {
    enemy.stars = -4
    assertEquals(enemy.stars, 2)
  }

  test("A Seagull wild unit should be able to increased their stars counter through the game") {
    enemy.stars += 3
    assertEquals(enemy.stars, 5)
  }

  test("A Seagull wild unit can lose stars through the game, so it should be able to decreased their stars counter") {
    enemy.stars += 3
    enemy.stars -= 2
    assertEquals(enemy.stars, 3)
  }

}
