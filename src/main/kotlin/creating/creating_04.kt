package org.example.creating

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

fun main() {
  println("Start timer.....")
  // timer will delay n seconds and emit 1 value is 0L and complete
  Observable.timer(/* delay = */ 5, /* unit = */ TimeUnit.SECONDS)
    .materialize()
    .subscribe(::println)

  Thread.sleep(10000)
  printSeparator()

  println("Start interval.....")
  // interval will delay every n seconds and emit 1 value and repeat
  val disposable: Disposable =
    Observable.interval(/* initialDelay = */ 2, /* period = */ 3, /* unit = */ TimeUnit.SECONDS)
      .materialize()
      .subscribe(::println)

  Thread.sleep(30000)
  disposable.dispose()
  printSeparator()

  // Schedulers.computation() is default Schedulers in timer and interval
}