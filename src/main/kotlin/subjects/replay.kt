package org.example.subjects

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.ReplaySubject

fun main() {
  val subject: ReplaySubject<Int> = ReplaySubject.createWithSize<Int>(2) // Buffer[]

  println("Buffer is ${subject.values.toList()}")

  // only observer 1 can receive 2 latest value
  subject.onNext(1) // Buffer[1]
  subject.onNext(2) // Buffer[1, 2]
  subject.onNext(3) // Buffer[2, 3]

  println("Buffer is ${subject.values.toList()}")

  Thread.sleep(1000)

  val disposable1: Disposable = subject.subscribe { v: Int? -> println("Observer 1 received: $v") }
  // only observer 1 can receive these values
  subject.onNext(4) // Buffer[3, 4]
  subject.onNext(5) // Buffer[4, 5]
  subject.onNext(6) // Buffer[5, 6]

  println("Buffer is ${subject.values.toList()}")

  Thread.sleep(1000)

  // only observer 2 can receive 2 latest value
  val disposable2: Disposable = subject.subscribe { v: Int? -> println("Observer 2 received: $v") }
  // observer 1 and observer 2 can receive these values
  subject.onNext(7) // Buffer[6, 7]
  subject.onNext(8) // Buffer[7, 8]
  subject.onNext(9) // Buffer[8, 9]

  println("Buffer is ${subject.values.toList()}")

  // observer 2 is disposed
  disposable2.dispose()

  Thread.sleep(1000)

  // only observer 1 can receive 2 latest value
  // only observer 1 can receive these values
  subject.onNext(10) // Buffer[9, 10]
  subject.onNext(11) // Buffer[10, 11]
  subject.onNext(12) // Buffer[11, 12]

  println("Buffer is ${subject.values.toList()}")

  // observer 1 is disposed
  disposable1.dispose()

  Thread.sleep(1000)

  // no observers can receive these values
  subject.onNext(13) // Buffer[12, 13]
  subject.onNext(14) // Buffer[13, 14]
  subject.onNext(15) // Buffer[14, 15]

  println("Buffer is ${subject.values.toList()}")
}
