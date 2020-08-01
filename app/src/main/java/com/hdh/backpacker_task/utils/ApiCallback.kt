package com.hdh.backpacker_task.utils

import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject

abstract class ApiCallback<M>() :
    DisposableObserver<M>() {

    private var loadingState: PublishSubject<Boolean>? = null

    constructor(loadingState: PublishSubject<Boolean>) : this() {
        this.loadingState = loadingState
    }

    abstract fun onSuccess(model: M)
    abstract fun onFailure(msg: String?)

    override fun onError(e: Throwable) {
        e.printStackTrace()
        loadingState?.onNext(false)
        onFailure("통신 상태가 원활하지 않습니다. 다시 새로고침 해주세요.")
    }

    override fun onNext(model: M) {
        loadingState?.onNext(false)
        onSuccess(model)
    }

    override fun onComplete() {}
}