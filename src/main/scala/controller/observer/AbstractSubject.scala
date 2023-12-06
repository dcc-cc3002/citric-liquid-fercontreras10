package cl.uchile.dcc.citric
package controller.observer

/** An abstract class that implements the `NormaSubject` interface as part of the NormaObserver design pattern.
  * This class provides a skeletal implementation of the `NormaSubject` interface, managing the registration
  * and notification of observers. It is intended to be extended by concrete subjects in the application.
  *
  * The NormaObserver design pattern is a software design pattern in which an object, called the subject, maintains
  * a list of its dependents, called observers, and notifies them automatically of any state changes, usually
  * by calling one of their methods. This pattern is commonly used for implementing distributed event handling
  * systems, in scenarios where an object needs to notify other objects without making assumptions regarding
  * those objects' types.
  *
  * @tparam T The type of the value that the observers are interested in.
  *
  * @constructor Initializes an empty list of observers.
  *
  * @author [[https://www.github.com/r8vnhill Ignacio Slater M.]]
  */
abstract class AbstractSubject[T] extends NormaSubject[T] {

  /** A list to hold registered observers. */
  private var observers: List[NormaObserver[T]] = List.empty

  /** Registers an observer to this subject.
    * @param observer The observer to be added to the list of registered observers.
    */
  override def addObserver(observer: NormaObserver[T]): Unit = {
    observers = observer :: observers
  }

  /** Notifies all registered observers with a new value.
   * Each observer's `update` method is called with the subject itself and the new value.
   *
   * @param value The value to be passed to the observers.
   */
  override def notifyObservers(value: T): Unit = {
    for (observer <- observers) {
      observer.update(this, value)
    }
  }
}
