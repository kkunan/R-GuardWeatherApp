package com.kkunan.weather.data.models

sealed class GetWeatherByLatLng {
    data class Request(val lat: Double, val lon: Double, val appid: String)

    data class Response(
        val base: String? = null,
        val clouds: Clouds? = null,
        val cod: Int? = null,
        val coord: Coord? = null,
        val dt: Int? = null,
        val id: Int? = null,
        val main: Main? = null,
        val name: String? = null,
        val sys: Sys? = null,
        val timezone: Int? = null,
        val visibility: Int? = null,
        val weather: List<Weather>? = null,
        val wind: Wind? = null
    )

    data class Clouds(
        val all: Int? = null
    )

    data class Coord(
        val lat: Int? = null,
        val lon: Int? = null
    )

    data class Main(
        val feels_like: Double? = null,
        val grnd_level: Int? = null,
        val humidity: Int? = null,
        val pressure: Int? = null,
        val sea_level: Int? = null,
        val temp: Double? = null,
        val temp_max: Double? = null,
        val temp_min: Double? = null
    )

    data class Sys(
        val country: String? = null,
        val sunrise: Int? = null,
        val sunset: Int? = null
    )

    data class Weather(
        val description: String? = null,
        val icon: String? = null,
        val id: Int? = null,
        val main: String? = null
    )

    data class Wind(
        val deg: Int? = null,
        val gust: Double? = null,
        val speed: Double? = null
    )
}