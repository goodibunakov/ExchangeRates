package ru.goodibunakov.exchangerates.data

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.goodibunakov.exchangerates.BuildConfig
import ru.goodibunakov.exchangerates.data.dto.CurrencyResponseDTO
import java.util.concurrent.TimeUnit


interface ApiService {


    @GET("daily_json.js")
    fun getData(): Observable<CurrencyResponseDTO>


    companion object {

        private const val BASE_URL = "https://www.cbr-xml-daily.ru/"


        fun create(): ApiService {
            val gson = GsonBuilder().setLenient().create()

            val httpClient = OkHttpClient.Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                        addInterceptor(httpLoggingInterceptor)
                    }
                    connectTimeout(50, TimeUnit.SECONDS)
                    readTimeout(50, TimeUnit.SECONDS)
                }
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}