package com.hdh.backpacker_task.ui.main.fragment

import com.hdh.backpacker_task.data.model.data.Location
import com.hdh.backpacker_task.ui.base.BasePresenter
import com.hdh.backpacker_task.utils.ApiCallback
import com.hdh.backpacker_task.utils.ApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class MainFragmentPresenter() : BasePresenter<MainFragmentView>() {

    private val searchSubject = BehaviorSubject.createDefault("se")
    private var isError : Boolean = false

    constructor(view: MainFragmentView) : this() {
        onAttach(view)

        sendRequest()
    }

    private fun sendRequest() {
        addSubscription(searchSubject
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { mView.mActivity.loadingState.onNext(true) }
            .observeOn(Schedulers.io())
            .switchMapSingle {
                ApiClient.mRetrofit.searchLocation(it)
            }
            .switchMapSingle {
                Observable.concatEager(
                    it.map { locationSearch ->
                        ApiClient.mRetrofit.searchWeather(locationSearch.woeid)
                            .subscribeOn(Schedulers.io())
                    }
                ).toList()
            }
            , object : ApiCallback<List<Location>>(mView.mActivity.loadingState){
                override fun onSuccess(model: List<Location>) {
                    mView.setRecyclerView(model)
                }

                override fun onFailure(msg: String?) {
                    mView.showToast(msg)
                    mView.onError()
                    isError = true
                }
            })
    }

    fun sendReSearch(){
        if (isError){
            sendRequest()
        } else{
            searchSubject.onNext("se")
        }
    }
}