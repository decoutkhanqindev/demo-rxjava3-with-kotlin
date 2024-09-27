package org.example

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
  val disposable: Disposable = Observable.just(1, 2, 3)
    .doOnEach {
      if (it.isOnNext) {
        println("doOnEach1 onNext ${it.value} and on thread ${Thread.currentThread().name}")
      }
    }
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.computation())
    .doOnEach {
      if (it.isOnNext) {
        println("doOnEach2 onNext ${it.value} and on thread ${Thread.currentThread().name}")
      }
    }
    .map { it * 2 }
    .doOnEach {
      if (it.isOnNext) {
        println("doOnEach3 onNext ${it.value} and on thread ${Thread.currentThread().name}")
      }
    }
    .subscribeOn(Schedulers.single())
    .observeOn(Schedulers.io())
    .filter { it % 2 == 0 }
    .doOnEach {
      if (it.isOnNext) {
        println("doOnEach4 onNext ${it.value} and on thread ${Thread.currentThread().name}")
      }
    }
    .subscribe()

  Thread.sleep(2000)
}