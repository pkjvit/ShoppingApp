package com.pkj.learn.asshopping.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pkj.learn.asshopping.data.source.ProductDataSource
import kotlinx.coroutines.delay

/**
 *
 * @author Pankaj Jangid
 */
public object FakeProductsRemoteDataSource : ProductDataSource {
    private const val SERVICE_LATENCY_IN_MILLIS = 2000L

    private var PRODUCTS_SERVICE_DATA = LinkedHashMap<String, Product>(5);

    init {
        addProduct("1", "Product 1", 103.5f, 0)
        addProduct("2", "Product 2", 106.5f, 0)
        addProduct("3", "Product 3", 123.5f, 1)
        addProduct("4", "Product 4", 108.2f, 0)
        addProduct("5", "Product 5", 101.0f, 5)
    }

    private val observableProduct = MutableLiveData<Result<List<Product>>>()

    override fun observeProducts(): LiveData<Result<List<Product>>> {
        return observableProduct
    }

    override suspend fun getProducts(): Result<List<Product>> {
        val products = PRODUCTS_SERVICE_DATA.values.toList()
        delay(SERVICE_LATENCY_IN_MILLIS)
        return Result.Success(products)
    }

    override suspend fun saveProduct(product: Product) {
        PRODUCTS_SERVICE_DATA[product.id] = product
    }

    override suspend fun likeProduct(productId: String, isLike: Boolean) {
        val oldProduct = PRODUCTS_SERVICE_DATA[productId]
        oldProduct?.let {
            PRODUCTS_SERVICE_DATA[it.id] = oldProduct.copy(likes = if(isLike) 1 else 0)
        }
    }

    override suspend fun offlineProduct(productId: String, isOffline: Boolean){
        val oldProduct = PRODUCTS_SERVICE_DATA[productId]
        oldProduct?.let {
            PRODUCTS_SERVICE_DATA[it.id] = oldProduct.copy(likes = if(isOffline) 1 else 0)
        }
    }

    private fun addProduct(id : String, name : String, price : Float, like : Int) {
        val product = Product(id, name, price, "", like)
        PRODUCTS_SERVICE_DATA[product.id] = product
    }


}