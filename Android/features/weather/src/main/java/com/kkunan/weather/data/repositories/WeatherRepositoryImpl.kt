package com.kkunan.weather.data.repositories

import com.kkunan.weather.data.datasources.WeatherNetworkDatasource
import com.kkunan.weather.domain.entities.Weather
import com.kkunan.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val datasource: WeatherNetworkDatasource) :
    WeatherRepository {
    override val nearbyWeather: Flow<Weather>
        = TODO("Not yet implemented")
}