package com.kkunan.weather.data.models

sealed class GetWeatherByLatLng {
    data class Request(val lat: Double, val lon: Double, val appid: String)

    data class Response(
        val base: String? = null,
        val clouds: Clouds? = null,
        val cod: Double? = null,
        val coord: Coord? = null,
        val dt: Double? = null,
        val id: Double? = null,
        val main: Main? = null,
        val name: String? = null,
        val sys: Sys? = null,
        val timezone: Double? = null,
        val visibility: Double? = null,
        val weather: List<Weather>? = null,
        val wind: Wind? = null
    )

    data class Clouds(
        val all: Double? = null
    )

    data class Coord(
        val lat: Double? = null,
        val lon: Double? = null
    )

    data class Main(
        val feels_like: Double? = null,
        val grnd_level: Double? = null,
        val humidity: Double? = null,
        val pressure: Double? = null,
        val sea_level: Double? = null,
        val temp: Double? = null,
        val temp_max: Double? = null,
        val temp_min: Double? = null
    )

    data class Sys(
        val country: String? = null,
        val sunrise: Double? = null,
        val sunset: Double? = null
    )

    data class Weather(
        val description: String? = null,
        val icon: String? = null,
        val id: Double? = null,
        val main: String? = null
    )

    data class Wind(
        val deg: Double? = null,
        val gust: Double? = null,
        val speed: Double? = null
    )
}