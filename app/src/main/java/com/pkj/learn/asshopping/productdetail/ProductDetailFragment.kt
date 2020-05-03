package com.pkj.learn.asshopping.productdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.data.Product
import com.pkj.learn.asshopping.util.getViewModelFactory
import kotlinx.android.synthetic.main.product_detail_fragment.view.*

class ProductDetailFragment : Fragment() {

    private val args: ProductDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<ProductDetailViewModel> {
        getViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        viewModel.getProductById(args.productId)
    }

    private fun setupObservers() {
        viewModel.product.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    private fun updateUI(product: Product){
        view?.findViewById<TextView>(R.id.name)?.let {
            it.text = product.name
        }
        view?.findViewById<ImageView>(R.id.image)?.let{
            Glide.with(it.context).load(product.image).into(it)
        }
        view?.findViewById<TextView>(R.id.details)?.let {
            it.text = product.detail
        }
        view?.findViewById<TextView>(R.id.price)?.let {
            it.text = "$ " + product.price
        }
        view?.findViewById<Button>(R.id.wishlist_btn)?.let{
            it.setOnClickListener {
            }
        }
    }

}
