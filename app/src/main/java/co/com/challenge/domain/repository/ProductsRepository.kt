package co.com.challenge.domain.repository

import androidx.lifecycle.MutableLiveData
import co.com.challenge.domain.models.ProductModel
import co.com.challenge.network.ApiClient
import co.com.challenge.network.Callback
import com.google.gson.Gson

/**
 * Created by Elkin Fracica on 1/11/21.
 */
class ProductsRepository {

    private val apiClient=  ApiClient
    private var products = MutableLiveData<List<ProductModel>>()

    suspend fun callProducts(query: String): Callback<Throwable, List<ProductModel>> = try {
        var listProduct : List<ProductModel>
        apiClient.getProducts(query).let {
            if (it.isSuccessful && it.body() != null) {
                val response = it.body()!!
                listProduct = Gson().fromJson(response["results"], Array<ProductModel>::class.java).toList()
            }else{
                listProduct = ArrayList()
            }
        }
        products.postValue(listProduct)
        Callback.onSuccess(listProduct)
    } catch (ex: Exception) {
        Callback.onFailed(ex)
    }

    fun getProducts(): MutableLiveData<List<ProductModel>> {
        return products
    }
}