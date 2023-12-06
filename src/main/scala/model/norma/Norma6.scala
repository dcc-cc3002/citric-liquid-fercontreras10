package cl.uchile.dcc.citric
package model.norma

/** This class represents the sixth level of Norma.
 * It extends the abstract class NormaLevel.
 * @constructor create a new Norma6 with the level 6.
 */
case class Norma6() extends NormaLevel {
  val level: Int = 6
  val stars: Int = 200
  val victories: Int = 14
}