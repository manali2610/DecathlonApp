package com.example.decathlon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Activity to host [ProductsFragment] which shows all the Decathlon products. */
class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_activity)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ProductsFragment.newInstance(), PRODUCT_FRAGMENT_TAG)
            .commit()
    }

    private companion object {
        const val PRODUCT_FRAGMENT_TAG = "Product Fragment Tag"
    }
}
