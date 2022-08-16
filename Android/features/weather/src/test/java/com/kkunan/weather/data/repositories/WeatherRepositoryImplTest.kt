package com.kkunan.weather.data.repositories

import com.kkunan.core.data.exceptions.ServerException
import com.kkunan.core.data.testutils.randomString
import com.kkunan.weather.BuildConfig
import com.kkunan.weather.data.datasources.WeatherNetworkDatasource
import com.kkunan.weather.data.models.GetWeatherByLatLng
import com.kkunan.weather.domain.entities.Weather
import io.mockk.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {
    private lateinit var repository: WeatherRepositoryImpl
    private lateinit var datasource: WeatherNetworkDatasource
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        datasource = mockk()
        repository = WeatherRepositoryImpl(datasource, dispatcher = testDispatcher)
    }

    @Test
    fun nearbyWeather_should_call_datasource_with_correct_params() =
        runTest(testDispatcher.scheduler) {
            val weather = GetWeatherByLatLng.Response()
            // arrange
            coEvery { datasource.getCurrentWeatherByLatLng(any()) } coAnswers { weather }

            // act
            repository.nearbyWeather(13.0, 100.0).collect {}

            // assert
            coVerify { datasource.getCurrentWeatherByLatLng(GetWeatherByLatLng.Request(
                lat = 13.0, lon = 100.0, appid = BuildConfig.OPEN_WEATHER_API_KEY
            )) }
        }

    @Test
    fun nearbyWeather_should_catch_exception_and_emit_null() =
        runTest(testDispatcher.scheduler) {
            // arrange
            val exception = ServerException.BadResponse("error!")
            coEvery { datasource.getCurrentWeatherByLatLng(any()) } throws exception

            // act
            val result = repository.nearbyWeather(13.0, 100.0).toList()
            assertNull(result[0])
        }

    @Test
    fun nearbyWeather_should_emit_null_if_weathers_empty() = runTest(testDispatcher.scheduler) {
        // arrange
        val weather = GetWeatherByLatLng.Response(weather = emptyList())
        // arrange
        coEvery { datasource.getCurrentWeatherByLatLng(any()) } coAnswers { weather }

        // act
        val result = repository.nearbyWeather(13.0, 100.0).toList()
        assertNull(result[0])
    }

    @Test
    fun nearbyWeather_should_map_weather_with_description() = runTest(testDispatcher.scheduler) {
        // arrange
        val mockDescription = randomString(10)
        val mockResult = mockk<GetWeatherByLatLng.Response> {
            every { weather } returns listOf(mockk {
                every { description } returns mockDescription
            })
        }
        coEvery { datasource.getCurrentWeatherByLatLng(any()) } coAnswers {
            mockResult
        }

        // act
        val result = repository.nearbyWeather(13.0, 100.0).toList()

        // assert
        assertEquals(Weather(mockDescription), result[0])
    }

    @Test
    fun nearbyWeather_should_emit_null_if_description_null() = runTest(testDispatcher.scheduler) {
        // arrange
        val mockResult = mockk<GetWeatherByLatLng.Response> {
            every { weather } returns listOf(mockk {
                every { description } returns null
            })
        }
        coEvery { datasource.getCurrentWeatherByLatLng(any()) } coAnswers {
            mockResult
        }

        // act
        val result = repository.nearbyWeather(13.0, 100.0).toList()
        println(result)

        // assert
        assertEquals(null, result[0])
    }
}