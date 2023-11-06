package cl.uchile.dcc.citric
package model.norma

/** This class represents the fifth level of Norma.
 * It extends the abstract class NormaLevel.
 * @constructor create a new Norma5 with the level 5.
 */
case class Norma5() extends NormaLevel {
  val level: Int = 5
  val stars: Int = 120
  val victories: Int = 10
}