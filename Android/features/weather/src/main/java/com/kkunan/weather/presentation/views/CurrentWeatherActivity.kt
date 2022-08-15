package com.kkunan.weather.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.kkunan.weather.data.datasources.OpenWeatherMapDatasource
import com.kkunan.weather.data.models.GetWeatherByLatLng
import com.kkunan.weather.data.services.OpenWeatherService
import com.kkunan.weather.presentation.views.ui.theme.RGuardTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentWeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val response = OpenWeatherMapDatasource(
                service = OpenWeatherService.instance
            ).getCurrentWeatherByLatLng(
                GetWeatherByLatLng.Request(
                    lat = 13.0,
                    lon = 100.0,
                    appid = "d287093b4703b8fa65f55b19a355c423"
                )
            )
            println(response)
        }


        setContent {
            RGuardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun WeatherEmoji(modifier: Modifier = Modifier, emojiUrl: String?) {
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(model = emojiUrl),
        contentDescription = "Emoji Url"
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RGuardTheme {
        Greeting("Android")
    }
}