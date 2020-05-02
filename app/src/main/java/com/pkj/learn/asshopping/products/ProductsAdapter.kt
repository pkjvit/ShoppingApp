package com.pkj.learn.asshopping.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.data.Product

/**
 * @author Pankaj Jangid
 */
class ProductsAdapter(private val viewModel: ProductsViewModel) : ListAdapter<Product, ProductViewHolder>(ProductItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindTo(viewModel, getItem(position))
    }

}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameText = itemView.findViewById<TextView>(R.id.name)
    private val likeView = itemView.findViewById<ImageButton>(R.id.like)
    private val offlineView = itemView.findViewById<ImageButton>(R.id.offline)

    fun bindTo(viewModel: ProductsViewModel, product: Product){
        nameText.text = product.name;
        updateLike(product.isLiked)
        updateOffline(product.isOffline)
        likeView.setOnClickListener{
            viewModel.likeProduct(product)
            updateLike(!product.isLiked)
        }
        offlineView.setOnClickListener{
            viewModel.offlineProduct(product)
            updateOffline(!product.isOffline)
        }
    }

    private fun updateLike(isLike: Boolean){
        likeView.setImageResource(if (isLike){
            R.drawable.ic_favorite_24px
        }else{
            R.drawable.ic_favorite_border_24px
        })
    }

    private fun updateOffline(isOffline: Boolean){
        offlineView.setImageResource(if(isOffline){
            R.drawable.ic_cloud_done_black_24dp
        }else{
            R.drawable.ic_cloud_download_black_24dp
        })
    }
}

class ProductItemDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product,
                                 newItem: Product): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product,
                                    newItem: Product): Boolean = oldItem == newItem

}