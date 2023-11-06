package cl.uchile.dcc.citric
package model.norma

/** Represents a norma level.
 * It has a level, a number of stars and a number of victories.
 *
 * @author [[https://github.com/fercontreras10]]
 */
trait NormaLevel {

    /** The norma level */
    val level: Int

    /** The number of stars required to reach this norma. */
    val stars: Int

    /** The number of victories required to reach this norma. */
    val victories: Int

}
