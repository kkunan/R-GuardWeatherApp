package com.kkunan.core.data.retrofit

import retrofit2.Converter
import retrofit2.Retrofit

fun <T> getRetrofitService(baseUrl: String, converterFactory: Converter.Factory?, cls: Class<T>) =
    Retrofit.Builder().baseUrl(baseUrl).apply {
        converterFactory?.let {
            addConverterFactory(it)
        }
    }.build().create(cls)