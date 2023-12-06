package cl.uchile.dcc.citric
package controller.states

import controller.GameController

class GameState protected (val context: GameController) {
  var chapter: Int = 1

  def takeAction(): Unit = {
    context.state = this
  }
}