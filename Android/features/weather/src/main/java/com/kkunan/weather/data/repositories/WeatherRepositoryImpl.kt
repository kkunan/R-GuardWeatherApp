package com.kkunan.weather.data.repositories

import com.kkunan.weather.BuildConfig
import com.kkunan.weather.data.datasources.WeatherNetworkDatasource
import com.kkunan.weather.data.models.GetWeatherByLatLng
import com.kkunan.weather.domain.entities.Weather
import com.kkunan.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val datasource: WeatherNetworkDatasource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherRepository {
    override fun nearbyWeather(latitude: Double, longitude: Double): Flow<Weather?> {
        return flow {
            callDatasource(latitude, longitude).weather?.let { weathers ->
                emitFirstWeather(weathers)
            }
        }.catch { emit(null) }.flowOn(dispatcher)
    }

    private suspend fun FlowCollector<Weather?>.emitFirstWeather(
        weathers: List<GetWeatherByLatLng.Weather>
    ) {
        if (weathers.isNotEmpty()) {
            emitWeather(weathers)
        } else {
            emit(null)
        }
    }

    private suspend fun FlowCollector<Weather?>.emitWeather(
        weathers: List<GetWeatherByLatLng.Weather>
    ) {
        weathers[0].description?.let {
            emit(Weather(it))
        } ?: run {
            emit(null)
        }
    }

    private suspend fun callDatasource(
        latitude: Double,
        longitude: Double
    ) = datasource.getCurrentWeatherByLatLng(
        GetWeatherByLatLng.Request(
            latitude, longitude,
            BuildConfig.OPEN_WEATHER_API_KEY
        )
    )

}