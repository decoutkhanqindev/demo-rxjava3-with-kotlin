package org.example.data_types

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

private fun callApi(): Single<String> {
  // return Single.error(RuntimeException("api not available"))

  return Single.fromCallable { // call when on subscribe
    println("callApi: current thread is ${Thread.currentThread().name}")
    Thread.sleep(1000)
    // throw RuntimeException("api not available")
    "data from api"
  }.subscribeOn(Schedulers.io())
}

fun main() {
  println("Start: current thread is ${Thread.currentThread().name}")
  Thread.sleep(2000)

  callApi().subscribe(
    /* onSuccess = */ { it: String ->
      println("onSuccess: current thread is ${Thread.currentThread().name}")
      println("onSuccess: $it")
    },
    /* onError = */ { it: Throwable ->
      println("onError: current thread is ${Thread.currentThread().name}")
      println("onError: ${it.message}")
    }
  )

  Thread.sleep(2000)
}