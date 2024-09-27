package org.example.subjects

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking() {
  // toSerialized help thread safe = synchronized
  val subject: Subject<Int?> = PublishSubject.create<Int>().toSerialized()

  launch {
    repeat(100) {
      launch(Dispatchers.IO) {
        subject.onNext(it)
      }
    }
  }
}