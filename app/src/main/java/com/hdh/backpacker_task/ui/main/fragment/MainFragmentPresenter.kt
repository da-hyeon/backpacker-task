package com.hdh.backpacker_task.ui.main.fragment

import com.hdh.backpacker_task.ui.base.BasePresenter
import com.hdh.backpacker_task.utils.ApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class MainFragmentPresenter() : BasePresenter<MainFragmentView>() {

    private val searchSubject = BehaviorSubject.createDefault("se")

    constructor(view: MainFragmentView) : this() {
        onAttach(view)

        sendRequest()
    }

    fun sendRequest() {
        addSubscription(searchSubject
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { mView.mActivity.loadingState.onNext(true) }
            .observeOn(Schedulers.io())
            .switchMapSingle {
                ApiClient.mRetrofit.searchLocation(it)
            }
            .switchMapSingle {
                Observable.merge(
                    it.map { locationSearch ->
                        ApiClient.mRetrofit.searchWeather(locationSearch.woeid)
                            .subscribeOn(Schedulers.io())
                    }
                ).toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { mView.mActivity.loadingState.onNext(false) }
            .subscribe {
                it.sort()
                mView.setRecyclerView(it)
            })
    }
}