package com.pkj.learn.asshopping.util

import androidx.fragment.app.Fragment
import com.pkj.learn.asshopping.ShoppingApp
import com.pkj.learn.asshopping.ViewModelFactory

/**
 * Extension functions for Fragment
 * @author Pankaj Jangid
 */


fun Fragment.getViewModelFactory(): ViewModelFactory{
    val repository = (requireContext().applicationContext as ShoppingApp).productsRepository
    return ViewModelFactory(repository, this)
}