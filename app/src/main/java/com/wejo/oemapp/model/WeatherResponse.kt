package com.wejo.oemapp.model
data class WeatherResponse(val dt: Int = 0,
                           val coord: Coord? = null,
                           val weather: List<WeatherItem>? = null,
                           val name: String = "",
                           val cod: Int = 0,
                           val main: Main? = null,
                           val clouds: Clouds? = null,
                           val id: Int = 0,
                           val sys: Sys? = null,
                           val base: String = "",
                           val wind: Wind? = null)