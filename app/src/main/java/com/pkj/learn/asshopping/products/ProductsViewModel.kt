package com.pkj.learn.asshopping.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkj.learn.asshopping.Event
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.source.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>(false)
    private val _openProductEvent = MutableLiveData<Event<String>>()

    val products = productsRepository.observeProducts()
    val openProductEvent: LiveData<Event<String>> = _openProductEvent

    init {
        viewModelScope.launch {
            productsRepository.getProducts(true)
        }
    }

    fun likeProduct(product: Product) {
        viewModelScope.launch {
            productsRepository.likeProduct(product.id, !product.isLiked)
        }
    }

    fun offlineProduct(product: Product) {
        viewModelScope.launch {
            productsRepository.offlineProduct(product.id, !product.isOffline)
        }
    }

    fun openProduct(productId : String){
        _openProductEvent.value = Event(productId)
    }
}
