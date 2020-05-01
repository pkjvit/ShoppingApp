package com.pkj.learn.asshopping.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.data.Product

/**
 * @author Pankaj Jangid
 */
class ProductsAdapter : ListAdapter<Product, ProductViewHolder>(ProductItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(product: Product){
        itemView.findViewById<TextView>(R.id.name).text = product.name;
    }

}

class ProductItemDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem

}