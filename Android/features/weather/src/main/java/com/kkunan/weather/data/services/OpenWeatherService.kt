package com.kkunan.weather.data.services

import com.kkunan.core.data.retrofit.getRetrofitService
import com.kkunan.weather.data.models.GetWeatherByLatLng
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("weather")
    suspend fun getWeatherByLatLng(
        @Query("appid") appId: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): GetWeatherByLatLng.Response

    companion object {
        val instance by lazy {
            getRetrofitService(
                baseUrl = "https://api.openweathermap.org/data/2.5/",
                converterFactory = GsonConverterFactory.create(),
                cls = OpenWeatherService::class.java
            )
        }
    }
}