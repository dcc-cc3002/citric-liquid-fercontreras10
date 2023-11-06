package cl.uchile.dcc.citric
package model.norma

/** This class represents the second level of Norma.
 * It extends the abstract class NormaLevel.
 * @constructor create a new Norma2 with the level 2.
 */
case class Norma2() extends NormaLevel {
  val level: Int = 2
  val stars: Int = 10
  val victories: Int = 1
}