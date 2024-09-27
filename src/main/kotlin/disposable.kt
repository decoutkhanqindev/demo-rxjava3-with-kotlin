package org.example

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

fun main() {
  val compositeDisposable = CompositeDisposable() // help combine Disposables to easy handle

  // interval() help every 1 second observable emits 1 value
  val timeObservable: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
  val timeObservable1: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)

  // subscribe() return obj Disposable
  val disposableTime: Disposable = timeObservable.subscribe(
    /* onNext = */ { println("disposableTime is onNext $it") },
    /* onError = */ { println("disposableTime is onError ${it.message}") },
    /* onComplete = */ { println("disposableTime is onComplete") },
  )

  val disposableTime1: Disposable = timeObservable.subscribe(
    /* onNext = */ { println("disposableTime1 is onNext $it") },
    /* onError = */ { println("disposableTime1 is onError ${it.message}") },
    /* onComplete = */ { println("disposableTime1 is onComplete") },
  )

  compositeDisposable.addAll(disposableTime, disposableTime1)

  println("before disposableTime uses dispose() isDisposed=${disposableTime.isDisposed}")
  println("before disposableTime1 uses dispose() isDisposed=${disposableTime1.isDisposed}")

  Thread.sleep(3000)

//  disposableTime.dispose()
//  disposableTime1.dispose()
  compositeDisposable.dispose()

  Thread.sleep(5000)

  println("after disposableTime uses dispose() isDisposed=${disposableTime.isDisposed}")
  println("after disposableTime1 uses dispose() isDisposed=${disposableTime1.isDisposed}")
}