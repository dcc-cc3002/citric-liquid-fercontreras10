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
 *  */
class PlayerTurn(context: GameController) extends GameState(context) {
  /*override def takeAction(): Unit = {
    // Roll dice
    val diceResult = context.currentPlayer.rollDice()
    println(s"${context.currentPlayer.name} rolled a $diceResult")

    // Move player
    context.movePlayer(diceResult)

    // Check if there is another character in the tile/panel
    val otherPlayer = context.checkForOtherPlayer()
    if (otherPlayer.isDefined) {
      // Decide if the player wants to combat
      val combat = new Combat(this)
      if (combat) {
        // Combat
        context.combat(otherPlayer.get)
      } else {
        // Execute the action of the tile/panel
        context.apply()
      }
    } else {
      // Execute the action of the tile/panel
      context.apply()
    }

    // End turn
    context.endTurn()

  }*/
}
