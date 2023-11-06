package cl.uchile.dcc.citric
package model.character

/** Represents a type of WildUnit.
 *
 * It has fixed stats and a base star quantity.
 * it gives a bono of 2 stars when defeated.
 *
 *  @constructor creates a new RoboBall with fixed stats and a base star quantity.
 *
 *  */
class RoboBall extends WildUnit("Robo Ball", 3, 3, -1, +1, -1, false,2) {

  val baseStarQuantity: Int = 2

}

