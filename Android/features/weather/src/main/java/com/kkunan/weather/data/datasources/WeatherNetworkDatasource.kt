package com.kkunan.weather.data.datasources

import com.kkunan.weather.data.models.GetWeatherByLatLngResponse
import kotlinx.coroutines.flow.Flow

interface WeatherNetworkDatasource {
    val response: Flow<GetWeatherByLatLngResponse>
}

class OpenWeatherMapDatasource: WeatherNetworkDatasource {
    override val response: Flow<GetWeatherByLatLngResponse>
        = TODO("Not yet implemented")
}