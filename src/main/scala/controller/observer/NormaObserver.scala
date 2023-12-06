package cl.uchile.dcc.citric
package controller.observer

/** A trait representing the NormaObserver part of the NormaObserver design pattern. In this pattern, an NormaObserver
  * is an object that wishes to be informed about events or changes in another object, known as the NormaSubject.
  * This trait provides a generic interface for objects that need to observe changes in Subjects.
  *
  * The NormaObserver design pattern is commonly used in scenarios where an object needs to automatically notify
  * a set of dependent objects about its state changes. It is a fundamental pattern for implementing distributed
  * event handling systems, where the subject is the source of events, and observers are the listeners/responders.
  *
  * Implementing this trait requires defining the `update` method, which is invoked when the NormaSubject that this
  * NormaObserver is registered with undergoes a change. The `update` method provides a mechanism for the NormaSubject to
  * send notifications to the NormaObserver, passing along any relevant information about the event or change.
  *
  * @tparam T The type of the value associated with the update notification. This type parameter allows the
  *           NormaObserver to be used in a type-safe manner with different kinds of subjects.
  */
trait NormaObserver[T] {

  /** Called by the NormaSubject that this NormaObserver is registered with whenever a relevant event or state change occurs.
    * This method should define how the NormaObserver responds to the update.
    *
    * @param observable The NormaSubject object that is notifying this NormaObserver of a change or event.
    * @param value The value or information associated with the update, of type `T`.
    */
  def update(observable: NormaSubject[T], value: T): Unit
}
