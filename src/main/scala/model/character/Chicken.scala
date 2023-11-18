package cl.uchile.dcc.citric
package model.character

/** Represents a type of WildUnit.
 *
 * It has fixed stats and a base star quantity.
 * it gives a bono of 3 stars when defeated.
 *
 *  @constructor creates a new Chicken with fixed stats and a base star quantity.
 *
 */
class Chicken extends WildUnit("Chicken", 3, 3, -1, -1, +1, false,3) {

  val baseStarQuantity: Int = 3
}
