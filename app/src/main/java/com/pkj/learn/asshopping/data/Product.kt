package com.pkj.learn.asshopping.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @author Pankaj Jangid
 */

@Entity(tableName = "products")
data class Product @JvmOverloads constructor(

    @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name="name") var name: String,
    @ColumnInfo(name="price") val price: Float,
    @ColumnInfo(name="detail") val detail: String,
    @ColumnInfo(name="likes") val likes: Int
){

    val isLiked: Boolean
        get() = likes>0
}