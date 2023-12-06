package cl.uchile.dcc.citric
package controller.states

import controller.GameController


/** Represent the game states of the game
 * @param context the context of the game.
 * */
class GameState protected (val context: GameController) {
  var chapter: Int = 1

  def takeAction(): Unit = {
    context.state = this
  }
}