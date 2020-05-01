package com.pkj.learn.asshopping.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.Result
import com.pkj.learn.asshopping.data.source.ProductDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Pankaj Jangid
 */
class ProductsLocalDataSource internal constructor(
    private val productsDao: ProductsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductDataSource{

    override fun observeProducts(): LiveData<Result<List<Product>>> {
        return productsDao.observeProducts().map { Result.Success(it) }
    }

    override suspend fun getProducts(): Result<List<Product>> = withContext(ioDispatcher) {
        return@withContext try{
            Result.Success(productsDao.getProducts())
        }catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun saveProduct(product: Product) = withContext(ioDispatcher) {
        productsDao.insertProduct(product)
    }
}