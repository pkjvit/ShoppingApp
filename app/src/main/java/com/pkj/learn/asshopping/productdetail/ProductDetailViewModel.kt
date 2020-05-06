package com.pkj.learn.asshopping.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.Result
import com.pkj.learn.asshopping.data.source.ProductsRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun getProductById(productId: String){
        viewModelScope.launch {
            productsRepository.getProduct(productId, true).let {
                when(it) {
                    is Result.Success -> _product.value = it.data
                }
            }
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


}
