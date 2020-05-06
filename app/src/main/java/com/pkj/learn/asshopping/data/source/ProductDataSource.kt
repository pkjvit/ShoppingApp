package com.pkj.learn.asshopping.data.source

import androidx.lifecycle.LiveData
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.Result

/** Main entry point for accessing products data
 *
 * @author Pankaj Jangid
 */
interface ProductDataSource {
    fun observeProducts(): LiveData<Result<List<Product>>>

    fun observeProduct(productId: String): LiveData<Result<Product>>

    suspend fun getProduct(productId: String) : Result<Product>

    suspend fun observeOfflineProducts(): LiveData<Result<List<Product>>>

    suspend fun observeCartProducts(): LiveData<Result<List<Product>>>

    suspend fun getProducts() : Result<List<Product>>

    suspend fun saveProduct(product: Product)

    suspend fun likeProduct(productId: String, isLike: Boolean)

    suspend fun offlineProduct(productId: String, isOffline: Boolean)
}