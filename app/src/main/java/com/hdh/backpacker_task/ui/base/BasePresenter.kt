package com.hdh.backpacker_task.ui.base

import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.View
import com.hdh.backpacker_task.R
import com.hdh.backpacker_task.data.ApiStores
import com.hdh.backpacker_task.utils.ApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

open class BasePresenter<V : BaseView> {

    protected lateinit var mView: V

    val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    open fun onAttach(view: V) {
        mView = view
    }

    open fun onDetach() {
        onUnSubscribe()
    }

    open fun onUnSubscribe() {
        compositeDisposable.dispose()
    }

    @SuppressLint("CheckResult")
    open fun addSubscription(observable: Observable<*>?, observer: DisposableObserver<*>) {
        compositeDisposable.add(observer)

        observable
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(observer as DisposableObserver<Any>)
    }

    open fun addSubscription(observer: Disposable) {
        compositeDisposable.add(observer)
    }
}