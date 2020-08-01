package com.hdh.backpacker_task.data.model.data

import kotlin.math.roundToInt

data class Location(
    val consolidated_weather : ArrayList<ConsolidatedWeather>,
    var title : String
) : Comparable<Location> {
    data class ConsolidatedWeather(
        val weather_state_name : String,
        val weather_state_abbr : String,
        val the_temp: Double,
        val humidity : Int
    ){
        fun getWeatherStateAbbr() : String = "https://www.metaweather.com/static/img/weather/png/64/${weather_state_abbr}.png"
        fun getTheTemp() : Int = the_temp.roundToInt()
    }

    override fun compareTo(other: Location): Int {
        return this.title.compareTo(other.title)
    }
}