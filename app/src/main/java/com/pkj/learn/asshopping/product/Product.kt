package com.pkj.learn.asshopping.product

/**
 * @author Pankaj Jangid
 */
data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val detail: String,
    val likes: Int
)