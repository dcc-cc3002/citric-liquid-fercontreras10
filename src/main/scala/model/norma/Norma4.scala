package cl.uchile.dcc.citric
package model.norma

/** This class represents the fourth level of Norma.
 * It extends the abstract class NormaLevel.
 * @constructor create a new Norma4 with the level 4.
 */
case class Norma4() extends NormaLevel {
  val level: Int = 4
  val stars: Int = 70
  val victories: Int = 6
}