package com.pkj.learn.asshopping.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pkj.learn.asshopping.data.Product

/**
 *  The Room database that contains the Product table.
 *
 * @author Pankaj Jangid
 */

//todo exportSchema should be true for production databases
@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase(){

    abstract fun productsDao(): ProductsDao

}