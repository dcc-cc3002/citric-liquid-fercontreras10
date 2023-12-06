package cl.uchile.dcc.citric
package controller.states

import controller.GameController

/** Represent the turns of a player in the game.
 * when the turn stars roll dice
 * then move the player
 * then check if there is another character in the tile/panel
 * if there is another character in the tile/panel decide if the player wants to combat
 * if not then execute the action of the tile/panel
 * end turn
 *
 * @param context the context of the game.
 */
class PlayerTurn(context: GameController) extends GameState(context) {
  override def takeAction(): Unit = {
    // Roll dice
    val diceResult = context.currentPlayer.rollDice()
    println(s"${context.currentPlayer.name} rolled a $diceResult")
  }
}
