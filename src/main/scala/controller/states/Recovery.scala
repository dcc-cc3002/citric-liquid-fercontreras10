package cl.uchile.dcc.citric
package controller.states

import controller.GameController

/** Represents the state of the game when a player is recovering.
 * The player should be able to recover in this state or not.
 * depending on the result of the dice it can recover or not.
 * @param context the context of the game.
 */
class Recovery(context: GameController) extends GameState(context) {
  override def takeAction(): Unit = {
    // Check if the player is in KO state
    if (context.currentPlayer.stateKO) {
      val diceResult = context.currentPlayer.rollDice()
      val recoveryTarget = 6 - context.currentPlayer.chapters

      if (diceResult >= recoveryTarget) {
        context.currentPlayer.recovery()
        println(s"${context.currentPlayer.name} has recovered!")
      } else {
        println(s"${context.currentPlayer.name} failed to recover.")
      }
    }

    context.state = new PlayerTurn(context)
  }
}
