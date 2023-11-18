package cl.uchile.dcc.citric
package model.norma

/** This class represents the third level of Norma.
 * It extends the abstract class NormaLevel.
 * @constructor create a new Norma3 with the level 3.
 */
case class Norma3() extends NormaLevel {
  val level: Int = 3
  val stars: Int = 30
  val victories: Int = 3
}