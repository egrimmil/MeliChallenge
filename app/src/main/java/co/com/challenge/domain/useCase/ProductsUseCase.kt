package co.com.challenge.domain.useCase

import co.com.challenge.domain.repository.ProductsRepository

/**
 * Created by Elkin Fracica on 1/11/21.
 */
class ProductsUseCase(private val productsRepository: ProductsRepository) {
    suspend fun call(query: String) = productsRepository.callProducts(query)

    fun get() = productsRepository.getProducts()
}