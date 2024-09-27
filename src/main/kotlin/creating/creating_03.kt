package org.example.creating

import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.core.Observable
import kotlin.Int
import kotlin.random.Random

fun main() {
  val coldObservable: Observable<Int> = Observable.defer {
    println("Call lambda outer.... ${Thread.currentThread()}")
    Observable.fromCallable {
      println("Call lambda inner.... ${Thread.currentThread()}")
      Thread.sleep(1000)
      Random.nextInt()
    }
  }

  println("Before subscribe.... ${Thread.currentThread()}")
  Thread.sleep(1000);

  repeat(3) { index: Int ->
    coldObservable.materialize().subscribe { value: Notification<Int> ->
      println("Subscriber ${index + 1}: $value")
    }
    Thread.sleep(1000)
    printSeparator()
  }
}