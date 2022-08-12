package com.kkunan.weather.data.services

import com.kkunan.core.data.retrofit.getRetrofitService
import com.kkunan.weather.data.models.GetWeatherByLatLngResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("weather")
    fun getWeatherByLatLng(
        @Query("appid") appId: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<GetWeatherByLatLngResponse?>?

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