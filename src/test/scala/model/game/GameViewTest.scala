package cl.uchile.dcc.citric
package model.game

class GameViewTest extends munit.FunSuite {

  test("Board should create GridPane with correct size") {
    val boardSize = 5
    val board = new Board(boardSize)
    val interiorSquares = (boardSize - 2) * (boardSize - 2)

    assertEquals(board.gridPane.children.size, boardSize * boardSize - interiorSquares)
  }

  test("movePlayer should update player position correctly along the border") {
    val player = new PlayerCircle
    val board = new Board(5)
    val movement = new Movement(player, board)

    // Test moving along the top border
    movement.movePlayer(3)
    assertEquals(movement.playerX, 3)
    assertEquals(movement.playerY, 0)

    // Test moving along the right border
    movement.movePlayer(2)
    assertEquals(movement.playerX, board.boardSize - 1)
    assertEquals(movement.playerY, 1)

    // Test moving along the bottom border
    movement.movePlayer(4)
    assertEquals(movement.playerX, 3)
    assertEquals(movement.playerY, board.boardSize - 1)

    // Test moving along the left border
    movement.movePlayer(1)
    assertEquals(movement.playerX, 2)
    assertEquals(movement.playerY, board.boardSize - 1)
  }



  test("PlayerCircle should have correct radius") {
    val playerCircle = new PlayerCircle
    assertEquals(playerCircle.circle.radius.value, 15.0)
  }

}
