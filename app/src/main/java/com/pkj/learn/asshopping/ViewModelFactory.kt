package com.pkj.learn.asshopping

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.pkj.learn.asshopping.data.source.ProductsRepository
import com.pkj.learn.asshopping.products.ProductsViewModel
import java.lang.IllegalArgumentException

/**
 * @author Pankaj Jangid
 */
class ViewModelFactory constructor(
    private val productsRepository: ProductsRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass){
        when {
            isAssignableFrom(ProductsViewModel::class.java) ->
                ProductsViewModel(productsRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }

    } as T

}