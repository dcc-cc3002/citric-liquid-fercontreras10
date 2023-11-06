package cl.uchile.dcc.citric
package model.norma

/** This class represents the first level of Norma.
  * It extends the abstract class NormaLevel.
  * @constructor create a new Norma1 with the level 1.
  */
case class Norma1() extends NormaLevel {
  val level: Int = 1
  val stars: Int = 0
  val victories: Int = 0
}
