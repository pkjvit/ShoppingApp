package com.pkj.learn.asshopping.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.products.ProductsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, ProductsFragment.newInstance())
            .commit();
    }
}
