package org.example.subjects

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.ReplaySubject

fun main() {
  val subject: ReplaySubject<Int> = ReplaySubject.createWithSize<Int>(2)
  // only observer 1 can receive 2 latest value
  subject.onNext(1)
  subject.onNext(2)
  subject.onNext(3)

  Thread.sleep(1000)

  val disposable1: Disposable = subject.subscribe { v: Int? -> println("Observer 1 received: $v") }
  // only observer 1 can receive these values
  subject.onNext(4)
  subject.onNext(5)
  subject.onNext(6)

  Thread.sleep(1000)

  val disposable2: Disposable = subject.subscribe { v: Int? -> println("Observer 2 received: $v") }
  // only observer 2 can receive 2 latest value
  // observer 1 and observer 2 can receive these values
  subject.onNext(7)
  subject.onNext(8)
  subject.onNext(9)
  // observer 2 is disposed
  disposable2.dispose()

  Thread.sleep(1000)

  // only observer 1 can receive these values
  subject.onNext(10)
  subject.onNext(11)
  subject.onNext(12)
  // observer 1 is disposed
  disposable1.dispose()

  Thread.sleep(1000)

  // no observers can receive these values
  subject.onNext(13)
  subject.onNext(14)
  subject.onNext(15)
}
