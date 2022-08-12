package com.kkunan.weather.data.datasources

import com.kkunan.weather.data.models.GetWeatherByLatLngResponse
import com.kkunan.weather.data.services.OpenWeatherService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WeatherNetworkDatasource {
    val response: Flow<GetWeatherByLatLngResponse>
}

class OpenWeatherMapDatasource @Inject constructor(private val service: OpenWeatherService) :
    WeatherNetworkDatasource {
    override val response: Flow<GetWeatherByLatLngResponse> = TODO("Not yet implemented")
}