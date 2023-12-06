
package cl.uchile.dcc.citric
package controller.observer

/**
 * A trait representing the NormaSubject part of the NormaObserver design pattern. In this pattern, a NormaSubject is an object
 * that maintains a list of its dependents, called Observers, and notifies them of any state changes. The `NormaSubject`
 * trait defines a generic interface for objects that are observed by Observers.
 *
 * The NormaObserver design pattern is widely used for creating an efficient and loosely coupled communication system
 * between objects in an application. It allows objects to observe and react to events or state changes in other objects
 * without requiring tight coupling or direct interaction between those objects.
 *
 * Implementing this trait requires providing definitions for two key methods: `addObserver`, which is used to register
 * an NormaObserver with the NormaSubject, and `notifyObservers`, which is used to notify all registered Observers of a state change
 * or event in the NormaSubject.
 *
 * @tparam T The type of the value that will be passed to Observers during notifications. This type parameter allows
 *           the NormaSubject to be used in a type-safe manner with different kinds of Observers.
 */
trait NormaSubject[T] {

  /**
   * Registers an NormaObserver to be notified of changes or events. This method adds an NormaObserver to the list of
   * dependents that are to be notified when the NormaSubject's state changes.
   *
   * @param observer The NormaObserver to be added to the list of registered Observers.
   */
  def addObserver(observer: NormaObserver[T]): Unit

  /**
   * Notifies all registered Observers of a state change or event in the NormaSubject. This method is typically called
   * by the NormaSubject itself whenever it undergoes a change that should be communicated to its Observers.
   *
   * @param value The value or information about the state change or event, of type `T`, to be passed to the Observers.
   */
  def notifyObservers(value: T): Unit
}
