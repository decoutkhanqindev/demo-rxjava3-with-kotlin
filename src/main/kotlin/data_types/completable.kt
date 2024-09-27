package org.example.data_types

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

private fun updateUser(user: User): Completable {
  return Completable.fromCallable {
    println("updateUser: current thread is ${Thread.currentThread().name}")
      when (user.id) {
        0 -> null // Completable can return anything Completable<?>
        else -> throw RuntimeException("Error when updating user $user")
    }
  }.subscribeOn(Schedulers.io())

//  return when (user.id) {
//    0 -> Completable.complete()
//    else -> Completable.error(RuntimeException("Error when updating user $user"))
//  }
}

fun main() {
  println("Start: current thread is ${Thread.currentThread().name}")
  Thread.sleep(2000)

  println("Start subscribing: current thread is ${Thread.currentThread().name}")
  Thread.sleep(500)

  val user = User(0, "A")
  updateUser(user).subscribe(
    /* onComplete = */ {
      println("onComplete: current thread is ${Thread.currentThread().name}")
      println("onComplete: update user success")
    },
    /* onError = */ { it: Throwable ->
      println("onError: current thread is ${Thread.currentThread().name}")
      println("onError: ${it.message}")
    },
  )

  Thread.sleep(500)
  println("-".repeat(120))

  val  user1= User(1, "B")
  updateUser(user1).subscribe(
    /* onComplete = */ {
      println("onComplete: current thread is ${Thread.currentThread().name}")
      println("onComplete: update user success")
    },
    /* onError = */ { it: Throwable ->
      println("onError: current thread is ${Thread.currentThread().name}")
      println("onError: ${it.message}")
    },
  )

  Thread.sleep(500)
  println("-".repeat(120))

  Thread.sleep(500)
  println("End subscribing: current thread is ${Thread.currentThread().name}")

  Thread.sleep(2000)
  println("End: current thread is ${Thread.currentThread().name}")
}