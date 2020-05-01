package com.pkj.learn.asshopping

import android.app.Application
import com.pkj.learn.asshopping.data.source.ProductsRepository

/**
 * @author Pankaj Jangid
 */

class ShoppingApp : Application() {

    val productsRepository : ProductsRepository
        get() = ServiceLocator.provideProductsRepository(this)


}