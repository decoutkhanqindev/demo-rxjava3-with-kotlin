package org.example.data_types.creating

import io.reactivex.rxjava3.core.Observable

fun printSeparator(): Unit = println("-------------------------------------")

fun main() {
  // just() includes 2 event is onNext() and onComplete()
  Observable.just("hello world")
    .materialize()
    .subscribe(::println)

  printSeparator()

  // fromIterable() includes 2 event is onNext() and onComplete()
  Observable.fromIterable(listOf("hello", "world"))
    .materialize()
    .subscribe(::println)

  printSeparator()

  Observable.fromIterable(setOf("C", "A", "A", "B"))
    .materialize()
    .subscribe(::println)

  printSeparator()
}