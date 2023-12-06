package cl.uchile.dcc.citric
package model.game

import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class PlayerCircle {
  val circle: Circle = new Circle {
    radius = 15
    fill = Color.Black
  }
}