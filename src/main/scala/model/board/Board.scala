package cl.uchile.dcc.citric
package model.board

/** The `Board` class is used to represent the board of the game.
 */
class Board {

  private val tiles: List[List[Option[Tile]]] = List(
    List(Some(new Tile("a1")), Some(new Tile("b1")), Some(new Tile("c1")), None, None, None, None, Some(new Tile("h1")), Some(new Tile("i1")), Some(new Tile("j1"))),
    List(Some(new Tile("a2")), None, Some(new Tile("c2")), Some(new Tile("d2")), Some(new Tile("e2")), Some(new Tile("f2")), Some(new Tile("g2")), Some(new Tile("h2")), None, Some(new Tile("j2"))),
    List(Some(new Tile("a3")), Some(new Tile("b2")), Some(new Tile("c3")), None, None, None, None, Some(new Tile("h3")), Some(new Tile("i2")), Some(new Tile("j3"))),
  )
  def drawBoard(): Unit = {
    for (row <- tiles) {
      for (tileOption <- row) {
        val symbol = tileOption.map(tile => s"${tile.name}${tile.panelType.map(panel => panel.name.substring(0, 1)).getOrElse("")}").getOrElse("  ")
        print(symbol + " ")
      }
      println()
    }
  }
}

/*object Main extends App {
  val board = new Board
  board.drawBoard()
}*/