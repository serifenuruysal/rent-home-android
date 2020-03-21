package com.ebayk.data.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */
private const val API_BASE_URL = "https://bff.ebay-kleinanzeigen.de/"

class ApiService {

    companion object {
        fun create(): Api {

            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    )
                )
                .client(providesOkHttpClient())
                .baseUrl(API_BASE_URL)
                .build()
                .create(Api::class.java)
        }

        private fun providesOkHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(BasicAuthInterceptor())
                .build()
        }
    }


}

class BasicAuthInterceptor : Interceptor {
    private val credentials: String = Credentials.basic("candidate", "yx6Xz62y")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }

}
