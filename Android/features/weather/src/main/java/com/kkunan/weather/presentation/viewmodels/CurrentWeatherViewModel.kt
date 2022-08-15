package com.kkunan.weather.presentation.viewmodels
import androidx.lifecycle.ViewModel
import com.kkunan.weather.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

class CurrentWeatherViewModel: ViewModel() {
    private val currentWeather: Flow<Weather> = TODO("NotImplementedError")
    val currentEmojiUrl: Flow<String?> = TODO("NotImplementedError")
}