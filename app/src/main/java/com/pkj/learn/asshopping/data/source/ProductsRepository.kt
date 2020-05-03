package com.pkj.learn.asshopping.data.source

import androidx.lifecycle.LiveData
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.Result

/**
 *  Interface to the data layer
 *
 * @author Pankaj Jangid
 */
interface ProductsRepository {

    fun observeProducts(): LiveData<Result<List<Product>>>

    fun observeProduct(productId: String): LiveData<Result<Product>>

    suspend fun getProduct(productId: String, forceUpdate: Boolean = false): Result<Product>

    suspend fun observeOfflineProducts(): LiveData<Result<List<Product>>>

    suspend fun observeCartProducts(): LiveData<Result<List<Product>>>

    suspend fun getProducts(forceUpdate: Boolean) : Result<List<Product>>

    suspend fun likeProduct(productId: String, isLike: Boolean)

    suspend fun offlineProduct(productId: String, isOffline: Boolean)
}