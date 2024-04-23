package com.example.decathlon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

/** Fragment which displays and helps search for all the Decathlon products. */
class ProductsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter

    private var productList = listOf<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            /* owner= */ this,
            ProductViewModelFactory()
        )[ProductViewModel::class.java]

        viewModel.productFetchResult.observe(/* owner= */ this, this::showProductDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.products_fragment, container, /* attachToRoot= */ false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProductDetails()
        productAdapter = ProductAdapter(requireContext())

        view.findViewById<TextInputLayout>(R.id.search_bar).apply {
            setupEditField(this.editText)
            setEndIconOnClickListener {
                productAdapter.setProductList(viewModel.performSearch(productList, this.editText?.text))
                productAdapter.notifyDataSetChanged()
            }
        }

        view.findViewById<MaterialButton>(R.id.sort_prices_button).setOnClickListener {
            productAdapter.setProductList(viewModel.sortByPrices(productList))
            productAdapter.notifyDataSetChanged()
        }

        view.findViewById<MaterialButton>(R.id.sort_names_button).setOnClickListener {
            productAdapter.setProductList(viewModel.sortByNames(productList))
            productAdapter.notifyDataSetChanged()
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.product_list).apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                /* reverseLayout= */ false
            )

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun showProductDetails(productFetchResult: ProductFetchResult) {
        if (productFetchResult == ProductFetchResult.Failure) {
            // Note: Can display an error message here
        } else {
            val success = productFetchResult as ProductFetchResult.Success

            productAdapter = ProductAdapter(requireContext())

            productList = success.productList
            productAdapter.setProductList(productList)

            recyclerView.adapter = productAdapter
        }
    }

    private fun setupEditField(editText: EditText?) {
        editText?.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    productAdapter.setProductList(viewModel.performSearch(productList, this.text))
                    productAdapter.notifyDataSetChanged()
                }
                true
            }
        }
    }

    companion object {
        /** Method to return the [ProductsFragment] object. */
        fun newInstance() = ProductsFragment()
    }
}
