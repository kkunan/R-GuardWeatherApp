package com.kkunan.weather.data.datasources

import com.google.gson.Gson
import com.kkunan.core.data.exceptions.ServerException
import com.kkunan.core.data.testutils.randomString
import com.kkunan.weather.data.models.GetWeatherByLatLng
import com.kkunan.weather.data.services.OpenWeatherService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import kotlin.math.exp
import kotlin.random.Random

@ExperimentalCoroutinesApi
class OpenWeatherMapDatasourceTest {
    private lateinit var service: OpenWeatherService
    private lateinit var datasource: OpenWeatherMapDatasource

    @Before
    fun setUp() {
        service = mockk()
        datasource = OpenWeatherMapDatasource(service)
    }

    @Test
    fun getCurrentWeatherByLatLng_should_call_service_getWeatherByLatLng_with_correct_request() =
        runTest {
            // arrange
            coEvery { service.getWeatherByLatLng(any(), any(), any()) } coAnswers {
                mockk()
            }
            val request = GetWeatherByLatLng.Request(
                lat = Random.nextDouble(),
                lon = Random.nextDouble(),
                appid = randomString(10)
            )

            // act
            datasource.getCurrentWeatherByLatLng(request)

            // assert
            coVerify {
                service.getWeatherByLatLng(lat = request.lat, lon = request.lon, appId = request.appid)
            }
        }

    @Test
    fun getCurrentWeatherByLatLng_should_throw_server_exception_if_service_has_exception() = runTest {
        // arrange
        coEvery { service.getWeatherByLatLng(any(), any(), any()) } throws Exception()
        val request = GetWeatherByLatLng.Request(
            lat = Random.nextDouble(),
            lon = Random.nextDouble(),
            appid = randomString(10)
        )

        try {
            // act
            datasource.getCurrentWeatherByLatLng(request)
            fail()
            // assert
        } catch (e: Exception){
            assertTrue(e is ServerException.BadResponse)
        }
    }

    @Test
    fun getCurrentWeatherByLatLng_should_return_server_result() = runTest {
        // arrange
        val expected = Gson().fromJson(output, GetWeatherByLatLng.Response::class.java)
        coEvery { service.getWeatherByLatLng(any(), any(), any()) } coAnswers {
            expected
        }

        val request = GetWeatherByLatLng.Request(
            lat = Random.nextDouble(),
            lon = Random.nextDouble(),
            appid = randomString(10)
        )

        // act
        val response = datasource.getCurrentWeatherByLatLng(request)

        // assert
        assertEquals(expected, response)

    }
}

private val output = """
   {"coord":{"lon":100,"lat":13},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"base":"stations","main":{"temp":300.23,"feels_like":301.97,"temp_min":300.23,"temp_max":300.23,"pressure":1006,"humidity":68,"sea_level":1006,"grnd_level":1005},"visibility":10000,"wind":{"speed":2.46,"deg":228,"gust":2.91},"clouds":{"all":100},"dt":1660051033,"sys":{"country":"TH","sunrise":1660000015,"sunset":1660045458},"timezone":25200,"id":1156648,"name":"Ban Lat","cod":200}
""".trimIndent()