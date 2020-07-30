package com.hdh.backpacker_task.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiStores {

    //https://www.metaweather.com/static/img/weather/png/64/sn.png

    @GET("location/search/")
    fun searchLocation(
        @Query("query") searchKeyWord : String
    ): Observable<Void>

    @GET("location/search/")
    fun searchWeather(
        @Query("query") searchKeyWord : String
    ): Observable<Void>
}