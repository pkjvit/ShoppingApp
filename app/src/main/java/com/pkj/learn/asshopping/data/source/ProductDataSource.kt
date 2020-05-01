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

    suspend fun getProducts() : Result<List<Product>>

    suspend fun saveProduct(product: Product)
}