package cl.uchile.dcc.citric
package model.norma


/** Represents .
 *
 * @author [[https://github.com/fercontreras10]]
 */
trait NormaType {

    /** The norma id */
    val normaId: Int

    /** The number of stars required to reach this norma. */
    val stars: Int

    /** The number of victories required to reach this norma. */
    val victories: Int

}
