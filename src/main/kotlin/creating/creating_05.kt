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

  // concat will emit 2 observable to 1 observable, first observable must onComplete ~ emitted so other observable just can emit
  val concatResult: Observable<String> =
    Observable.concat(observable1, observable2, Observable.just("[3] last"))
  concatResult.materialize().subscribe(::println)

  Thread.sleep(10000)
}