package com.hdh.kakao_pay_task.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class ClickUtil(lifecycle: Lifecycle, private val delay: Long = 500L) : LifecycleObserver {
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var clickSubject: BehaviorSubject<(() -> Unit)>

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun create() {
        compositeDisposable = CompositeDisposable()
        clickSubject = BehaviorSubject.create()
        compositeDisposable.add(
            clickSubject.throttleFirst(delay, TimeUnit.MILLISECONDS)
                .subscribe {
                    it.invoke()
                })
    }

    //fragment lifecycle 2가지
    //어디에 등록하는지에 따라 상황이 바뀔 수 있다.

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun destroy() {
        compositeDisposable.dispose()
    }

    fun run(hof: () -> Unit) {
        clickSubject.onNext(hof)
    }
}