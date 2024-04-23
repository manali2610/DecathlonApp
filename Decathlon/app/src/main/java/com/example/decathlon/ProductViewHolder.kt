package com.example.decathlon

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/** [RecyclerView.ViewHolder] for displaying product list item. */
class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Method to display the product item.
     *
     * @param product the [ProductModel] to be displayed
     * @param position the position of the product item
    */
    fun bind(product: ProductModel, position: Int) {
        itemView.apply {
            val imageUrl = if (position % 2 == 0) {
                "https://picsum.photos/seed/picsum/200/300"
            } else {
                "https://picsum.photos/id/237/200/300"
            }

            Glide.with(this.context)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_background)
                )
                .into(findViewById(R.id.product_image))

            val context = itemView.context

            findViewById<TextView>(R.id.product_name).text = product.name
            findViewById<TextView>(R.id.product_brand).text =
                String.format(context.getString(R.string.product_brand_text), product.brand)
            findViewById<TextView>(R.id.product_price).text = product.price.toString()
        }
    }
}
