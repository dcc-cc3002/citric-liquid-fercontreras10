package cl.uchile.dcc.citric
package controller

import controller.states.GameState
import model.character.PlayerCharacter
import model.game.TurnSystem

class GameController {

  private val state: GameState = _
  private val turnSystem: TurnSystem = new TurnSystem(List[PlayerCharacter]())

  def startGame(): Unit = {
    turnSystem.startGame()
    println("Game Started")
  }

  def rollDice(): Unit = {
    turnSystem.currentPlayer.rollDice()
    println("Roll Dice")
  }

  def playTurn(): Unit = {
    turnSystem.nextPlayerTurn()
    println("Play Turn")
  }

  def changeState(newState: GameState): Unit = {
    val state = newState
    state.controller = this
  }

  def endGame(): Unit = {

  }

}
