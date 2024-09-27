package org.example.data_types

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription

fun main() {
  Flowable.fromIterable(0..10000)
    // If the observer cannot handle data in time
    .onBackpressureLatest() // only handle latest value
    .onBackpressureBuffer() // value will contain in buffer
    .onBackpressureDrop() // value will drop
    .subscribe(object : FlowableSubscriber<Int> {
      lateinit var subscription: Subscription // help observer can handle send request to observable

      override fun onSubscribe(s: Subscription) {
        println("onSubscribe")
        this.subscription = s
        this.subscription.request(1)
      }

      override fun onNext(value: Int?) {
        println("onNext $value")
        Thread.sleep(2000)
        this.subscription.request(1)
      }

      override fun onError(t: Throwable?) {
        println("onError $t")
      }

      override fun onComplete() {
        println("onComplete")
      }
    })
}