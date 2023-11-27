package cl.uchile.dcc.citric
package controller.states

import controller.GameController

trait GameState {

  var controller: GameController
  def startGame(): Unit = { /* ... */ }
  def rollDice(): Unit = { /* ... */ }

}
