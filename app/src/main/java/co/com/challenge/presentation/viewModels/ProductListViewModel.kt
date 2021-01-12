package co.com.challenge.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.challenge.domain.models.ProductModel
import co.com.challenge.domain.useCase.ProductsUseCase
import co.com.challenge.presentation.state.State
import co.com.challenge.ui.adapters.ListProductsAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

/**
 * Created by Elkin Fracica on 1/11/21.
 */
class ProductListViewModel(private val getProductsUseCase: ProductsUseCase) : ViewModel() {

    private val disposable = CompositeDisposable()
    private var listMoviesAdapter: ListProductsAdapter? = null
    private val _stateGetProductsList = MutableLiveData<State>()
    val stateGetProductsList: LiveData<State>
        get() = _stateGetProductsList

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

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

    fun getProductsAdapter(): ListProductsAdapter? {
        listMoviesAdapter = ListProductsAdapter()
        return listMoviesAdapter
    }
}