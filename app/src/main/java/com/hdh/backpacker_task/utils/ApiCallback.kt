package com.hdh.kakao_pay_task.utils

import com.facebook.stetho.common.LogUtil
import com.google.gson.JsonSyntaxException
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException

abstract class ApiCallback<M> :
    DisposableObserver<M>() {

    abstract fun onSuccess(model: M)
    abstract fun onFailure(msg: String?)

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val code = e.code()
                var msg = e.message
                LogUtil.d("code=$code")
                if (code == 500 || code == 502 || code == 404 || code == 504) {
                    msg = "통신 상태가 원활하지 않습니다 잠시 후 다시 시도해 주세요."
                }
                onFailure(msg)
            }
            //검색 결과가 없을 시 items의 타입이 Array 타입에서 Object로 변경 됨
            is JsonSyntaxException -> {
                onFailure("검색 결과가 없습니다.")
            }
            else -> {
                onFailure(e.message)
            }
        }
    }

    override fun onNext(model: M) {
        onSuccess(model)
    }

    override fun onComplete() {}
}