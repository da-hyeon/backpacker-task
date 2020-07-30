package com.hdh.backpacker_task.data.model.data

data class Location(
    val consolidatedWeatherList : ArrayList<ConsolidatedWeather>
){
    data class ConsolidatedWeather(
        var title : String,
        val weather_state_name : String,
        val weather_state_abbr : String,
        val the_temp: String,
        val humidity : String
    )
}