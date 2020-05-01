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

    suspend fun getProducts(forceUpdate: Boolean) : Result<List<Product>>
}