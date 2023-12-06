package cl.uchile.dcc.citric
package controller.states

import controller.GameController

/**
 * Represents the state of the game before the game starts.
 * The board should be initialized in this state.
 * The players order should be decided in this state.
 * @param context the context of the game.
 */
class PreGame(context: GameController) extends GameState(context) {
  override def takeAction(): Unit = {
    context.initializeBoard()
    context.initializePlayersOrder()
    context.state = new Chapter(context)
  }
}
