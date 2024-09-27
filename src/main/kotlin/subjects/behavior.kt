package org.example.subjects

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

fun main() {
  // using in state management: UI state (ViewModel), ....
  val subject: BehaviorSubject<Int> = BehaviorSubject.createDefault<Int>(0)

  println("Values is ${subject.value}")

  //  only observer 1 can receive latest value is 3
  subject.onNext(1)
  subject.onNext(2)
  subject.onNext(3)

  println("Values is ${subject.value}")

  Thread.sleep(1000)

  val disposable1: Disposable = subject.subscribe { v: Int? -> println("Observer 1 received: $v") }
  // only observer 1 can receive these values
  subject.onNext(4)
  subject.onNext(5)
  subject.onNext(6)

  println("Values is ${subject.value}")

  Thread.sleep(1000)

  val disposable2: Disposable = subject.subscribe { v: Int? -> println("Observer 2 received: $v") }
  // observer 2 can receive latest value is 6
  // both observer 1 and observer 2 can receive these values
  subject.onNext(7)
  subject.onNext(8)
  subject.onNext(9)

  println("Values is ${subject.value}")

  // observer 2 is disposed
  disposable2.dispose()

  Thread.sleep(1000)

  // only observer 1 can receive latest value is 9
  // only observer 1 can receive these values
  subject.onNext(10)
  subject.onNext(11)
  subject.onNext(12)

  println("Values is ${subject.value}")

  // observer 1 is disposed
  disposable1.dispose()

  Thread.sleep(1000)

  // no observers can receive latest value is 12
  // no observers can receive these values
  subject.onNext(13)
  subject.onNext(14)
  subject.onNext(15)

  println("Values is ${subject.value}")
}