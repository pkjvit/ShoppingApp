package com.pkj.learn.asshopping.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pkj.learn.asshopping.data.Product

/**
 * Data access object for the products table
 *
 * @author Pankaj Jangid
 */
@Dao
interface ProductsDao {


    /**
     * Observe list of Products
     *
     * @return all products
     */
    @Query("SELECT * FROM Products")
    fun observeProducts(): LiveData<List<Product>>

    /**
     * Select all products from table
     *
     * @return all products
     */
    @Query("SELECT * FROM Products")
    suspend fun getProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)
}