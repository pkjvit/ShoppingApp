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

    override fun observeProduct(productId: String): LiveData<Result<Product>> {
        return productsDao.observeProductById(productId).map { Result.Success(it) }
    }

    override suspend fun getProduct(productId: String): Result<Product>  = withContext(ioDispatcher){
        try{
            val product = productsDao.getProductById(productId)
            product?.let {
               return@withContext Result.Success(product)
            }
            return@withContext Result.Error(java.lang.Exception("Product not found"))
        }catch (e: Exception){
            return@withContext Result.Error(e)
        }
    }

    override suspend fun observeOfflineProducts(): LiveData<Result<List<Product>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun observeCartProducts(): LiveData<Result<List<Product>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override suspend fun likeProduct(productId: String, isLike: Boolean) {
        productsDao.updateProductLike(productId, if(isLike) 1 else 0)
    }

    override suspend fun offlineProduct(productId: String, isOffline: Boolean) {
        productsDao.updateProductOffline(productId, if(isOffline) 1 else 0)
    }
}