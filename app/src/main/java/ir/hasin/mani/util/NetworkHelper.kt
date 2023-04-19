package ir.hasin.mani.util

import com.google.gson.GsonBuilder
import ir.hasin.mani.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun getOkHttpClient() = when {
    BuildConfig.DEBUG -> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }.build()
    }
    else -> {
        OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url =
                    originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }.build()
    }
}

fun getRetrofitClient(
    BASE_URL: String,
    okHttpClient: OkHttpClient,
): Retrofit = Retrofit.Builder().addConverterFactory(
    GsonConverterFactory.create(
        GsonBuilder().create()
    )
).baseUrl(BASE_URL).client(okHttpClient).build()





