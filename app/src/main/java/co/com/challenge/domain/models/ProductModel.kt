package co.com.challenge.domain.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Elkin Fracica on 1/11/21.
 */
data class ProductModel(
    @SerializedName("id")
    val id : String = "",
    @SerializedName("title")
    val title : String = "",
    @SerializedName("price")
    val price : Float = 0F,
    @SerializedName("thumbnail")
    val thumbnail : String = "",
    @SerializedName("original_price")
    val original_price : Float = 0F,
    @SerializedName("seller")
    val seller: SellerModel = SellerModel()
)

data class SellerModel(
    @SerializedName("permalink")
    val permalink: String = "",
    @SerializedName("seller_reputation")
    val seller_reputation: SellerReputacionModel = SellerReputacionModel()
)

data class SellerReputacionModel(
    @SerializedName("power_seller_status")
    val power_seller_status: String = ""
)