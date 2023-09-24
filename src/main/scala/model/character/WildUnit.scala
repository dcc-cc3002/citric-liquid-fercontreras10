package cl.uchile.dcc.citric
package model.character

import scala.util.Random

class WildUnit (val name: String,
                val maxHp: Int,
                val currentHP: Int,
                val attack: Int,
                val defense: Int,
                val evasion: Int,
                var stars: Int = 0) {

  /** Increases the number of stars the player has by the given amount.
   *
   */
  def increaseStars(amount: Int): Unit = {
    stars += amount
  }

}
