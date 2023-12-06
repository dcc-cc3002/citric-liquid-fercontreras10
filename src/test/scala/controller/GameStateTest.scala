package cl.uchile.dcc.citric
package controller

import controller.states.{Chapter, PreGame}
import model.character.PlayerCharacter
import model.game.TurnSystem
import model.norma.Norma1

import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GameStateTest extends munit.FunSuite {

  test("PreGame should initialize board and order players") {
    val gameController = new GameController()
    val preGameState = new PreGame(gameController)
    gameController.initializePlayersOrder()
    preGameState.takeAction()
    assert(gameController.state.isInstanceOf[Chapter])
  }

  /*test("Chapter should increment chapter after all players' turns are finalized") {
    val gameController = new GameController()
    gameController.initializePlayersOrder()
    gameController.nextPlayerTurn()
    val chapterState = new Chapter(gameController)
    assert(gameController.state.isInstanceOf[Chapter])
    assert(gameController.state.chapter == 2)
  }*/



}
