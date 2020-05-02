package com.pkj.learn.asshopping.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query("SELECT * FROM Products WHERE id = :id")
    suspend fun getProductById(id: String): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Query("UPDATE Products SET likes = :likes WHERE id = :productId")
    suspend fun updateProductLike(productId: String, likes: Int)

    @Query("UPDATE Products SET offline = :offline WHERE id = :productId")
    suspend fun updateProductOffline(productId: String, offline: Int)
}