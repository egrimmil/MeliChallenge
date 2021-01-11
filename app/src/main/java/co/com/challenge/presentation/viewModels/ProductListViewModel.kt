package co.com.challenge.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.challenge.domain.useCase.ProductsUseCase
import co.com.challenge.presentation.state.State
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

/**
 * Created by Elkin Fracica on 1/11/21.
 */
class ProductListViewModel(private val getProductsUseCase: ProductsUseCase) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _stateGetProductsList = MutableLiveData<State>()
    val stateGetProductsList: LiveData<State>
        get() = _stateGetProductsList

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getProducts(){
        _stateGetProductsList.value = State.Loading
        viewModelScope.launch {

        }
    }
}