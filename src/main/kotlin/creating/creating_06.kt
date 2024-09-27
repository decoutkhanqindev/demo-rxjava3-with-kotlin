package org.example.creating

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun main() {
  val observable1: Observable<String> =
    Observable.range(2, 9) // 9 - 2 + 1 values
    .map { "[1] $it" }
    .doOnSubscribe {
      println("Subscribe to observable 1.....${Thread.currentThread()}")
    }

  val observable2: Observable<String> =
    Observable.interval(1, TimeUnit.SECONDS)
    .take(3)
    .map { "[2] $it" }
    .doOnSubscribe {
      println("Subscribe to observable 2.....${Thread.currentThread()}")
    }

  // zip will aggregate 2 observables together into 1 common observable and it follows the index
  val zipped: Observable<String> =
    Observable.zip(observable1, observable2, { t1, t2 -> "$t1, $t2" })
  zipped.materialize().subscribe(::println)

  Thread.sleep(10000)
}