package cl.uchile.dcc.citric
package model.game

object Norma  {

  case object Norma1 extends NormaType {
    val normaId: Int = 1
    val stars: Int = 0
    val victories: Int = 0
  }

  case object Norma2 extends NormaType {
    val normaId: Int = 2
    val stars: Int = 10
    val victories: Int = 1
  }

  case object Norma3 extends NormaType {
    val normaId: Int = 3
    val stars: Int = 30
    val victories: Int = 3
  }
  case object Norma4 extends NormaType {
    val normaId: Int = 4
    val stars: Int = 70
    val victories: Int = 6
  }
  case object Norma5 extends NormaType {
    val normaId: Int = 5
    val stars: Int = 120
    val victories: Int = 10
  }
  case object Norma6 extends NormaType {
    val normaId: Int = 6
    val stars: Int = 200
    val victories: Int = 14
  }
}