package org.example.subjects

import io.reactivex.rxjava3.subjects.AsyncSubject

fun main() {
  // keep last value , emit it only onComplete
  val subject: AsyncSubject<Int?> = AsyncSubject.create<Int>()

  subject.materialize().subscribe { println("Observer 1 receive $it") }

  repeat(100) {
    println("onNext $it")
    subject.onNext(it)

    if (it == 50) {
      subject.materialize().subscribe { println("Observer 2 receive $it") }
    }
  }

  println("Before onComplete")
  subject.onComplete() // emit 99 to all observers
  println("After onComplete")

  Thread.sleep(1000)
  println("Done")

  subject.materialize().subscribe { println("Observer 3 receive $it") }
  Thread.sleep(1000)
}