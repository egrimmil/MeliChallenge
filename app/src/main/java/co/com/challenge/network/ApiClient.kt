package co.com.challenge.network

import co.com.challenge.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor

/**
 * Created by Elkin Fracica on 1/11/21.
 */
object ApiClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Api Request")
                .response("Api Response")
                .build()
        )
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)

    private val apiService: ApiService = Retrofit.Builder()
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create(ApiService::class.java)


    suspend fun getProducts(query: String) = apiService.getProducts(query)

}