package cl.uchile.dcc.citric
package model.game

/** Represents .
 *
 * @author [[https://github.com/fercontreras10]]
 */
trait NormaType {

    /** The norma */
    def normaId: Int

    /** The number of stars required to reach this norma. */
    def stars: Int

    /** The number of victories required to reach this norma. */
    def victories: Int
}

