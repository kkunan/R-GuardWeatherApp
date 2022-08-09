package com.kkunan.weather.domain.repositories

import com.kkunan.weather.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    val nearbyWeather: Flow<Weather>
}