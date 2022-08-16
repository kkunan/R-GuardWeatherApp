package com.kkunan.weather.domain.repositories

import com.kkunan.weather.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun nearbyWeather(latitude: Double, longitude: Double): Flow<Weather?>
}