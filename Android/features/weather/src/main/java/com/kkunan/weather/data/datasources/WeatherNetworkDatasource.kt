package com.kkunan.weather.data.datasources

import com.kkunan.core.data.exceptions.ServerException
import com.kkunan.weather.data.models.GetWeatherByLatLng
import com.kkunan.weather.data.services.OpenWeatherService
import javax.inject.Inject

interface WeatherNetworkDatasource {
    suspend fun getCurrentWeatherByLatLng(request: GetWeatherByLatLng.Request): GetWeatherByLatLng.Response
}

class OpenWeatherMapDatasource @Inject constructor(private val service: OpenWeatherService) :
    WeatherNetworkDatasource {
    override suspend fun getCurrentWeatherByLatLng(request: GetWeatherByLatLng.Request): GetWeatherByLatLng.Response {
        return try {
            service.getWeatherByLatLng(lat = request.lat, lon = request.lon, appId = request.appid)
        } catch (e: Exception){
            throw ServerException.BadResponse(e.localizedMessage ?: "")
        }
    }
}