package org.example.data_types

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers

data class User(val id: Int, val name: String)

private fun findUserById(id: Int): Maybe<User> {
  return Maybe.fromCallable {
    println("findUserById id=$id: current thread is ${Thread.currentThread().name}")
    when (id) {
      0 -> null
      1 -> User(id = 1, name = "A")
      else -> throw RuntimeException("Error when finding user by id=$id")
    }
  }.subscribeOn(Schedulers.io())

//  return when (id) {
//    0 -> Maybe.empty()
//    1 -> Maybe.just(User(id = 1, name = "A"))
//    else -> Maybe.error(RuntimeException("Error when finding user by id=$id"))
//  }.subscribeOn(Schedulers.io())
}

fun main() {
  println("Start: current thread is ${Thread.currentThread().name}")
  Thread.sleep(2000)

  println("Start subscribing: current thread is ${Thread.currentThread().name}")
  Thread.sleep(500)
  repeat(3) { id: Int ->
    findUserById(id).subscribe(
      /* onSuccess = */ { it: User ->
        println("onSuccess id=$id: current thread is ${Thread.currentThread().name}")
        println("onSuccess id=$id: $it")
      },
      /* onError = */ { it: Throwable ->
        println("onError id=$id: current thread is ${Thread.currentThread().name}")
        println("onError id=$id: ${it.message}")
      },
      /* onComplete = */ {
        println("onComplete id=$id current thread is ${Thread.currentThread().name}")
        println("onComplete id=$id: null")
      }
    )
    Thread.sleep(500)
    println("-".repeat(120))
  }
  Thread.sleep(500)
  println("End subscribing: current thread is ${Thread.currentThread().name}")

  Thread.sleep(2000)
  println("End: current thread is ${Thread.currentThread().name}")
}