package com.pkj.learn.asshopping

import android.content.Context
import androidx.room.Room
import com.pkj.learn.asshopping.data.FakeProductsRemoteDataSource
import com.pkj.learn.asshopping.data.source.DefaultProductsRepository
import com.pkj.learn.asshopping.data.source.ProductDataSource
import com.pkj.learn.asshopping.data.source.ProductsRepository
import com.pkj.learn.asshopping.data.source.local.ProductsLocalDataSource
import com.pkj.learn.asshopping.data.source.local.ShoppingDatabase

/**
 * A Service Locator for the [ProductsRepository]. This is the mock version, with a
 * [FakeProductsRemoteDataSource]
 * @author Pankaj Jangid
 */
object ServiceLocator {

    private var database: ShoppingDatabase? = null

    @Volatile
    var productRepository: ProductsRepository? = null


    fun provideProductsRepository(context: Context): ProductsRepository {
        synchronized(this){
            return productRepository
                ?: productRepository
                ?: createProductsRepository(
                    context
                )
        }
    }

    private fun createProductsRepository(context: Context): ProductsRepository {
        val newRepo = DefaultProductsRepository(FakeProductsRemoteDataSource, createProductsLocalDataSource(context))
        productRepository = newRepo
        return newRepo
    }

    private fun createProductsLocalDataSource(context: Context): ProductDataSource {
        val database = database ?: createDatabase(context)
        return ProductsLocalDataSource(database.productsDao())
    }

    private fun createDatabase(context: Context): ShoppingDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            ShoppingDatabase::class.java,
            "Shopping.db"
        ).build();
        database = result;
        return result
    }

}