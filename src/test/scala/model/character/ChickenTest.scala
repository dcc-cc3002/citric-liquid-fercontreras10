package cl.uchile.dcc.citric
package model.character

class ChickenTest extends munit.FunSuite {

  private val name = "Chicken"
  private val maxHp = 3
  private val currentHp = 3
  private val attack = -1
  private val defense = -1
  private val evasion = +1
  private val stateVisible = false
  private val stars = 3

  private var enemy: WildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    enemy = new Chicken
  }

  test("A Chicken wild unit should have correctly set their attributes") {
    assertEquals(enemy.name, name)
    assertEquals(enemy.maxHp, maxHp)
    assertEquals(enemy.currentHp, currentHp)
    assertEquals(enemy.attack, attack)
    assertEquals(enemy.defense, defense)
    assertEquals(enemy.evasion, evasion)
    assertEquals(enemy.stateVisible, stateVisible)
    assertEquals(enemy.stars, stars)
  }

  test("An enemy currentHp should be less than or equal to their maxHP") {
    assert(enemy.currentHp <= enemy.maxHp)
  }

  test("An enemy currentHP can't be less than 0") {
    enemy.currentHp = -3
    assertEquals(enemy.currentHp, 0)
  }

  test("An enemy should enter Visible state when their currentHP is 0") {
    enemy.currentHp = 0
    enemy.Hide()
    assertEquals(enemy.stateVisible, true)
  }

  test("A Chicken wild unit should have 3 stars at the beginning of the game") {
    assertEquals(enemy.stars, 3)
  }

  test("A Chicken wild unit stars can't be less than 3") {
    enemy.stars = -4
    assertEquals(enemy.stars, 3)
  }

  test("A Chicken wild unit should be able to increased their stars counter through the game") {
    enemy.stars += 3
    assertEquals(enemy.stars, 6)
  }

  test("A Chicken wild unit can lose stars through the game, so it should be able to decreased their stars counter") {
    enemy.stars += 3
    enemy.stars -= 2
    assertEquals(enemy.stars, 4)
  }

}
