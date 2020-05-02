package com.pkj.learn.asshopping.data.source

import androidx.lifecycle.LiveData
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Default implementation of [ProductsRepository]. Single entry point for managing product's data.
 *
 * @author Pankaj Jangid
 */
class DefaultProductsRepository(
    private val productRemoteDataSource: ProductDataSource,
    private val productsLocalDataSource: ProductDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO ) : ProductsRepository {

    override fun observeProducts(): LiveData<Result<List<Product>>> {
        return productsLocalDataSource.observeProducts()
    }

    override suspend fun getProducts(forceUpdate: Boolean): Result<List<Product>> {
        if (forceUpdate) {
            try {
                updateProductsFromRemoteDataSource()
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }
        return productsLocalDataSource.getProducts()
    }

    override suspend fun likeProduct(productId: String, isLike: Boolean) {
        productsLocalDataSource.likeProduct(productId, isLike)
        productRemoteDataSource.likeProduct(productId, isLike)
    }

    override suspend fun offlineProduct(productId: String, isOffline: Boolean) {
        productsLocalDataSource.offlineProduct(productId, isOffline)
        productRemoteDataSource.offlineProduct(productId, isOffline)
    }


    private suspend fun updateProductsFromRemoteDataSource() {
        val remoteProducts = productRemoteDataSource.getProducts()

        when(remoteProducts){
            is Result.Success -> remoteProducts.data.forEach { product ->
                productsLocalDataSource.saveProduct(product)
            }
            is Result.Error -> remoteProducts.exception
        }
    }
}