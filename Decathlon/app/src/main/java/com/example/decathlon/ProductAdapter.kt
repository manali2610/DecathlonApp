package com.example.decathlon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/** [RecyclerView.Adapter] to show the product list. */
class ProductAdapter(
    private val context: Context,
) : RecyclerView.Adapter<ProductViewHolder>() {

    private lateinit var productList: List<ProductModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.product_layout, parent, /* attachToRoot= */ false)
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position], position)
    }

    /**
     * Method to set the list of products to be shown on the recycler view.
     *
     * @param products of products to be shown
    */
    fun setProductList(products: List<ProductModel>) {
        productList = products
    }
}
