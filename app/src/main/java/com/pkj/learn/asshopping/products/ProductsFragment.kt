package com.pkj.learn.asshopping.products

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.data.Result
import com.pkj.learn.asshopping.util.getViewModelFactory
import kotlinx.android.synthetic.main.products_fragment.*

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() =
            ProductsFragment()
    }

    private val viewModel by viewModels<ProductsViewModel> { getViewModelFactory() }


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
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){
        products_recycler.adapter = ProductsAdapter()
        products_recycler.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun setupObservers(){
        viewModel.getProducts().observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> (products_recycler.adapter as ProductsAdapter).submitList(it.data)
            }
        })
    }

}
