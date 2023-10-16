package cl.uchile.dcc.citric
package model.character

abstract class WildUnit (val name: String,
                val maxHp: Int,
                var currentHP: Int,
                val attack: Int,
                val defense: Int,
                val evasion: Int,
                var stars: Int = 0) extends Entity {

  /** Increases the number of stars the WildUnit has by the given amount.
   *
   * @param amount the amount of stars to increase.
   */
  def increaseStars(amount: Int): Unit = {
    stars += amount
  }

}
