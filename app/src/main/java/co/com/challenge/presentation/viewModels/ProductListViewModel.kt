package co.com.challenge.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import co.com.challenge.domain.models.ProductModel
import co.com.challenge.domain.repository.ProductsRepository
import co.com.challenge.domain.useCase.ProductsUseCase
import co.com.challenge.presentation.state.State
import co.com.challenge.ui.adapters.ListProductsAdapter
import co.com.challenge.ui.fragments.FirstFragmentDirections
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

/**
 * Created by Elkin Fracica on 1/11/21.
 */
class ProductListViewModel : ViewModel() {

    private val getProductsUseCase = ProductsUseCase(ProductsRepository())
    var listMoviesAdapter: ListProductsAdapter? = null
    private val _stateGetProductsList = MutableLiveData<State>()
    val stateGetProductsList: LiveData<State>
        get() = _stateGetProductsList

    fun callProducts(query: String) {
        _stateGetProductsList.value = State.Loading
        viewModelScope.launch {
            getProductsUseCase.call(query).callback(
                ::getProductsFailure,
                ::getProductsSuccess
            )
        }
    }

    private fun getProductsFailure(failure: Throwable) {
        _stateGetProductsList.value = State.Failed(failure)
    }

    private fun getProductsSuccess(products: List<ProductModel>) {
        if (products.isNullOrEmpty()) _stateGetProductsList.value = State.Empty
        else _stateGetProductsList.value = State.Success(products)
    }

    fun getPoducts(): MutableLiveData<List<ProductModel>> {
        return getProductsUseCase.get()
    }

    fun setProductsAdapter(products: List<ProductModel>) {
        listMoviesAdapter?.updateData(products)
        listMoviesAdapter?.notifyDataSetChanged()
    }
}