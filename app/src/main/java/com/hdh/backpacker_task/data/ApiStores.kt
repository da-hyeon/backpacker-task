package com.hdh.backpacker_task.data

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiStores {
    @GET("v1/nid/me")
    fun naverLogin(
        @Header("Authorization") token: String
    ): Observable<Void>
}