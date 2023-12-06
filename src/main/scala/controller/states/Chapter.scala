package cl.uchile.dcc.citric
package controller.states

import controller.GameController


/** Represent the state chapter of the game.
 * The chapter should be initialized in this state.
 * When all the players finalized their turn, the chapter should be incremented.
 * @param context the context of the game.
 *  */
class Chapter(context: GameController) extends GameState(context) {
  override def takeAction(): Unit = {
    println(s"Chapter ${context.state.chapter}")

    for (_ <- 1 to context.players.length) {
      context.state = new PlayerTurn(context)
      context.state.takeAction()
    }

    context.state = new Chapter(context)
  }
}
