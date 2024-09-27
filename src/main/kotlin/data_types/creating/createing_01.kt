package org.example.data_types.creating

import io.reactivex.rxjava3.core.Observable

fun printSeparator(): Unit = println("-------------------------------------")

fun main() {
  Observable.just("hello world")
    .materialize()
    .subscribe(::println)

  printSeparator()

  Observable.fromIterable(listOf("hello", "world"))
    .materialize()
    .subscribe(::println)

  printSeparator()

  Observable.fromIterable(setOf("C", "A", "A", "B"))
    .materialize()
    .subscribe(::println)

  printSeparator()
}