package cl.uchile.dcc.citric
package model.character

/** The WildUnit class is an abstract class that represent an enemy in the game.
 *
 * A WildUnit is a type of Entity that can be found in the game.
 * There's a total of 3 WildUnits in the game: RoboBall, Seagull and Chicken.
 * They have fixed stats for each wild unit (hp, atk, def, eva) and a base star quantity.
 *
 * @param _name the unique name of the WildUnit.
 * @param _maxHp the maximum hit points of the WildUnit.
 * @param _currentHp the current hit points of the WildUnit.
 * @param _attack the attack of the WildUnit.
 * @param _defense the defense of the WildUnit.
 * @param _evasion the evasion of the WildUnit.
 * @param _stateVisible the state of the WildUnit.
 * @param _stars the stars of the WildUnit.
 *
 * @author [[https://github.com/fercontreras10/ Fernanda Contreras C.]]
 */
abstract class WildUnit(private val _name: String,
                        private val _maxHp: Int,
                        private var _currentHp: Int,
                        private val _attack: Int,
                        private val _defense: Int,
                        private val _evasion: Int,
                        private var _stateVisible: Boolean = false,
                        private var _stars: Int = 0) extends AbstractEntity {

  /** Returns the unique name of the WildUnit. */
  def name: String = _name

  /** Returns the maximum hit points of the WildUnit. */
  def maxHp: Int = _maxHp

  /** Returns the current hit points of the WildUnit. */
  def currentHp: Int = _currentHp

  /** Updates the current hit points of the WildUnit.
   *
   * If the new value is less than 0, the current hit points are set to 0.
   *
   * @param newCurrentHp the new current hit points of the WildUnit.
   */
  def currentHp_=(newCurrentHp: Int): Unit = {
    _currentHp = Math.max(0, newCurrentHp)
  }

  /** Returns the attack of the WildUnit. */
  def attack: Int = _attack

  /** Returns the defense of the WildUnit. */
  def defense: Int = _defense

  /** Returns the evasion of the WildUnit. */
  def evasion: Int = _evasion

  /** Returns the state of the WildUnit. */
  def stateVisible: Boolean = _stateVisible

  /** Hide is a method that is called when a WildUnit hit points are 0.
   *
   * This function calls the setter to update the Visible state of the WildUnit.
   */
  def Hide(): Unit = {
    if (_currentHp == 0) {
      _stateVisible = true
    }
  }

  /** Returns the stars of the WildUnit. */
  def stars: Int = _stars

  /** Returns the base star quantity of the WildUnit. */
  def baseStarQuantity: Int

  /** Updates the stars of the WildUnit.
   *
   * If the new value is less than 0, the stars are set to 0.
   *
   * @param newStars the new stars of the WildUnit.
   */
  def stars_=(newStars: Int): Unit = {
    _stars = Math.max(baseStarQuantity, newStars)
  }

}
