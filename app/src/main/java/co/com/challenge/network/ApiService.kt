package co.com.challenge.network

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Elkin Fracica on 1/11/21.
 */
interface ApiService {

    companion object{
        const val search = "sites/MLA/search"
    }

    @GET(search)
    suspend fun getProducts(@Query("q") query: String): Response<JsonObject>
}