package com.pkj.learn.asshopping.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pkj.learn.asshopping.EventObserver
import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.data.Result
import com.pkj.learn.asshopping.util.getViewModelFactory
import kotlinx.android.synthetic.main.products_fragment.*


class ProductsFragment : Fragment() {

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
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.openProductEvent.observe(viewLifecycleOwner, EventObserver{
            openProduct(it)
        })
    }

    private fun openProduct(productId: String) {
        val action = ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(productId)
        findNavController().navigate(action)
    }

    private fun setupRecyclerView(){
        products_recycler.apply {
            setHasFixedSize(true)
            adapter = ProductsAdapter(viewModel)
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

    private fun setupObservers(){
        viewModel.products.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> (products_recycler.adapter as ProductsAdapter).submitList(it.data)
            }
        })
    }



}
