package com.pkj.learn.asshopping.products

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.product.Product
import kotlinx.android.synthetic.main.products_fragment.*

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() =
            ProductsFragment()
    }

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.products_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        products_recycler.adapter = ProductsAdapter()
        products_recycler.layoutManager = GridLayoutManager(activity, 2)
        val products = ArrayList<Product>(100)
        for(i in 0..100){
            products.add(Product("id"+i, "name "+i, i*1.0f, "detail "+i, 0));
        }
        (products_recycler.adapter as ProductsAdapter).submitList(products)
    }

}
