package cl.uchile.dcc.citric
package model.board

import model.character.PlayerCharacter

/** The `Tile` class represents a tile on the board.
 * is used to represent the board of the game.
 */
class Tile(val name:String, var player: Option[PlayerCharacter] = None, var panelType: Option[Panel] = None)

