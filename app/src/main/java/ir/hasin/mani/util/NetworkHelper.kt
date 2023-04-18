package ir.hasin.mani.util

import com.google.gson.GsonBuilder
import ir.hasin.mani.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

fun getOkHttpClient() = when {
    BuildConfig.DEBUG -> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
            AuthenticationInterceptor()
        ).connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()
    }
    else -> {
        OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).addInterceptor(AuthenticationInterceptor()).build()
    }
}

class AuthenticationInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest =
            request.newBuilder().header("api_key", BuildConfig.API_KEY).build()
        return chain.proceed(authenticatedRequest)
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





