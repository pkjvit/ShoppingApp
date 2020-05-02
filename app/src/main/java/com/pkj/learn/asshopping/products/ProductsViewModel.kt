package com.pkj.learn.asshopping.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.Result
import com.pkj.learn.asshopping.data.source.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private val product = productsRepository.observeProducts()
    private val _dataLoading = MutableLiveData<Boolean>(false)

    fun getProducts() : LiveData<Result<List<Product>>> {
        viewModelScope.launch {
            productsRepository.getProducts(true)
        }
        return product
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

}
