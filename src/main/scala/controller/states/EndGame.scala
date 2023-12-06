package cl.uchile.dcc.citric
package controller.states

import controller.GameController

/** Represent the end game state of the game.
 * The game should be finalized in this state.
 *
 * @param context the context of the game.
 */
class EndGame(context: GameController) extends GameState(context) {
  override def takeAction(): Unit = {
    context.endGame()
  }

  override def equals(obj: Any): Boolean = {
    obj != null && obj.isInstanceOf[EndGame]
  }
}
