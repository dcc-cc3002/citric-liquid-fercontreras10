package cl.uchile.dcc.citric
package model.game

class Movement(player: PlayerCircle, board: Board) {
  // Player initial position
  private[game] var playerX = 0
  private[game] var playerY = 0

  // Update player position on the board
  private def updatePlayerPosition(): Unit = {
    board.gridPane.children.remove(player.circle)
    board.gridPane.add(player.circle, playerX, playerY)
  }

  // Move the player based on the dice result
  def movePlayer(steps: Int): Unit = {
    // Move the player along the border
    for (_ <- 1 to steps) {
      if (playerY == 0 && playerX < board.boardSize - 1) {
        // Move along the top border
        playerX += 1
      } else if (playerX == board.boardSize - 1 && playerY < board.boardSize - 1) {
        // Move along the right border
        playerY += 1
      } else if (playerY == board.boardSize - 1 && playerX > 0) {
        // Move along the bottom border
        playerX -= 1
      } else if (playerX == 0 && playerY > 0) {
        // Move along the left border
        playerY -= 1
      }
      updatePlayerPosition()
    }
  }
}
