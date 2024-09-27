package org.example

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

fun main() {
  /*
    // observable is producer
    val observable: Observable<Int> = Observable.just(1, 2, 3, 4, 5) // just() to emit value

    // observer is consumer
    val observer: Observer<Int> = object : Observer<Int> {
      override fun onSubscribe(d: Disposable) {
        println("onSubscribe isDisposed=${d.isDisposed}")
      }

      override fun onNext(t: Int) {
        println("onNext $t")
      }

      override fun onError(e: Throwable) {
        println("onError ${e.message}")
      }

      override fun onComplete() {
        println("onComplete")
      }
    }

    // observer subscribes to observable
    observable.subscribe(observer)
   */

  // use operators
  val disposable: Disposable = Observable.just(1, 2, 0, 4, 5, 0)
    .map { item: Int -> 10 / item }
    .onErrorReturnItem(-1)
    .subscribe(
      /* onNext = */ { println("onNext $it") },
      /* onError = */ { println("onError cause ${it.message}") },
      /* onComplete = */ { println("onComplete") }
    )
}