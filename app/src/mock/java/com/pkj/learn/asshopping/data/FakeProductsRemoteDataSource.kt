package com.pkj.learn.asshopping.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.pkj.learn.asshopping.data.source.ProductDataSource
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 *
 * @author Pankaj Jangid
 */
public object FakeProductsRemoteDataSource : ProductDataSource {
    private const val SERVICE_LATENCY_IN_MILLIS = 2000L

    private var PRODUCTS_SERVICE_DATA = LinkedHashMap<String, Product>(5);

    init {
        addProduct("1", "Chilli", 103.5f, "https://www.bhf.org.uk/-/media/new-site-images/informationsupport/heart-matters/december-2019/news/bth/chilli-peppers/chilli-peppers-620x400-ss-noexp.jpg", 0)
        addProduct("2", "Tomato", 106.5f, "https://cdnk.nurserylive.com/images/stories/virtuemart/product/nurserylive-tomato-round.jpg",0)
        addProduct("3", "Potato", 123.5f, "https://media.supermart.ae/7898-large_default/potato-lebanon-500g.jpg",1)
        addProduct("4", "Peanut", 108.2f, "https://5.imimg.com/data5/PD/GE/MY-44379607/shell-groundnut-peanut-250x250.jpg", 0)
        addProduct("5", "Onion", 101.0f, "https://pictures.grocerapps.com/original/grocerapp-onion--5e6d35a65de02.jpeg", 5)
    }

    private val observableProducts = MutableLiveData<Result<List<Product>>>()

    override fun observeProducts(): LiveData<Result<List<Product>>> {
        return observableProducts
    }

    override fun observeProduct(productId: String): LiveData<Result<Product>> {
        return observableProducts.map { products ->
            when (products) {
                is Result.Loading -> Result.Loading
                is Result.Error -> Result.Error(products.exception)
                is Result.Success -> {
                    val product = products.data.firstOrNull() { it.id == productId }
                        ?: return@map Result.Error(Exception("Product not found"))
                    Result.Success(product)
                }
            }
        }
    }

    override suspend fun getProduct(productId: String): Result<Product> {
        PRODUCTS_SERVICE_DATA[productId]?.let {
            return Result.Success(it)
        }
        return Result.Error(Exception("Product not found"))
    }

    override suspend fun observeOfflineProducts(): LiveData<Result<List<Product>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun observeCartProducts(): LiveData<Result<List<Product>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    private fun addProduct(id : String, name : String, price : Float, image : String, like : Int) {
        val product = Product(id, name, price, "", image, like)
        PRODUCTS_SERVICE_DATA[product.id] = product
    }


}