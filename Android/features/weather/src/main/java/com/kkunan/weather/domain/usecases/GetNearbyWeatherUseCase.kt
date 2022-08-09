package com.kkunan.weather.domain.usecases

import com.kkunan.weather.domain.entities.Weather
import com.kkunan.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNearbyWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    val currentWeather: Flow<Weather> = TODO("throw NotImplementedError")
}