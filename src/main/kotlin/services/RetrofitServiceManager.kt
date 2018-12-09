package services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitServiceManager {

    private var retrofit: Retrofit

    companion object {
        const val DEFAULT_TIME_OUT: Long = 5
        const val DEFAULT_READ_TIME_OUT: Long = 10

        class SingletonHolder {
            companion object {
                val INSTANCE = RetrofitServiceManager()
            }
        }
    }

    init {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        builder.addInterceptor(logger)

        builder.addInterceptor(AddCookiesInterceptor())
                .addInterceptor(CacheInterceptor())
                .addNetworkInterceptor(CacheNetworkInterceptor())

        retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInstance(): RetrofitServiceManager {
        return SingletonHolder.INSTANCE
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}