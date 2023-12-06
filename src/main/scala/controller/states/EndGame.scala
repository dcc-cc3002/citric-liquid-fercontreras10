package cl.uchile.dcc.citric
package controller.states

import controller.GameController

class EndGame(context: GameController) extends GameState(context) {
  override def takeAction(): Unit = {
    context.endGame()
  }

  override def equals(obj: Any): Boolean = {
    obj != null && obj.isInstanceOf[EndGame]
  }
}
