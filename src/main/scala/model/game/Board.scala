package cl.uchile.dcc.citric
package model.game

import model.board.{BonusPanel, DropPanel, EncounterPanel, NeutralPanel, Panel}
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class Board(val boardSize: Int) {
  private val bonusPanel = new BonusPanel
  private val dropPanel = new DropPanel
  private val encounterPanel = new EncounterPanel
  private val neutralPanel = new NeutralPanel

  val gridPane: GridPane = new GridPane {
    for {
      col <- 0 until boardSize
      row <- 0 until boardSize
    } {
      val panel: Panel = if (col == 0 || row == 0 || col == boardSize - 1 || row == boardSize - 1) {
        if (util.Random.nextBoolean()) bonusPanel
        else if (util.Random.nextBoolean()) dropPanel
        else if (util.Random.nextBoolean()) encounterPanel
        else neutralPanel
      } else {
        null
      }

      if (panel != null) {
        val square = new Rectangle {
          width = 60
          height = 60
          fill = panel match {
            case _: BonusPanel => Color.Green
            case _: DropPanel => Color.Red
            case _: EncounterPanel => Color.Blue
            case _: NeutralPanel => Color.White
          }
          stroke = Color.Black
          strokeWidth = 1.0
        }
        add(square, col, row)
      }
    }
  }
}
