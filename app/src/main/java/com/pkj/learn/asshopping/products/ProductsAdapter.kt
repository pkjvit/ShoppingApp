package com.pkj.learn.asshopping.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pkj.learn.asshopping.R
import com.pkj.learn.asshopping.data.Product
import kotlinx.android.synthetic.main.layout_product.view.*

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
    fun bindTo(viewModel: ProductsViewModel, product: Product){
        Glide.with(itemView.context).load(product.image).into(itemView.image)
        itemView.name.text = product.name;
        itemView.price.text = product.priceLabel
        updateLike(product.isLiked)
        updateOffline(product.isOffline)
        itemView.like.setOnClickListener{
            viewModel.likeProduct(product)
            updateLike(!product.isLiked)
        }
        itemView.offline.setOnClickListener{
            viewModel.offlineProduct(product)
            updateOffline(!product.isOffline)
        }
        itemView.setOnClickListener{
            viewModel.openProduct(product.id)
        }
    }

    private fun updateLike(isLike: Boolean){
        itemView.like.setImageResource(if (isLike){
            R.drawable.ic_favorite_24px
        }else{
            R.drawable.ic_favorite_border_24px
        })
    }

    private fun updateOffline(isOffline: Boolean){
        itemView.offline.setImageResource(if(isOffline){
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