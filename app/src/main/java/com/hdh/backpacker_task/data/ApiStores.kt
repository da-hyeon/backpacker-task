package com.hdh.backpacker_task.data

import com.hdh.backpacker_task.data.model.data.Location
import com.hdh.backpacker_task.data.model.data.LocationSearch
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiStores {
    @GET("location/search/")
    fun searchLocation(
        @Query("query") searchKeyWord : String
    ): Single<List<LocationSearch>>


    @GET("location/{woeid}/")
    fun searchWeather(
        @Path("woeid") woeid : String
    ): Observable<Location>
}