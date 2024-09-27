package org.example.subjects

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.concurrent.TimeUnit

fun main() {
  val subject: Subject<Int> = PublishSubject.create()

  // Property 1: is a hot observable with behavior === subscribe(...) + Multicast -> share 1 data producer to all observers
  subject.subscribe { v: Int -> println("Observer 1: $v") }
  subject.subscribe { v: Int -> println("Observer 2: $v") }
  subject.subscribe { v: Int -> println("Observer 3: $v") }

  // Property 2: is an observer === onNext/onError/onComplete
  subject.onNext(1) // onNext event emits 1 to all observers
  subject.onNext(2) // onNext event emits 2 to all observers
  subject.onNext(3) // onNext event emits 3 to all observers

  // Forward the values from another Observable to the Subject
  Observable.interval(1, java.util.concurrent.TimeUnit.SECONDS)
    .take(3)
    .map { (it + 4).toInt() } // 4, 5, 6 => subject => all observers
    .subscribe(subject)

  Thread.sleep(5000)
  subject.onComplete()
}