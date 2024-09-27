package org.example.data_types.creating

import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.random.Random

fun main() {
  // lambda will call when it subscribed and not share with other subscribers
  // -> will init observable with independent logic
  // -> cold observable
  val coldObservable: Observable<Int> = Observable.fromCallable {
    println("Call lambda.... ${Thread.currentThread()}")
    Thread.sleep(1000)
    Random.nextInt()
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