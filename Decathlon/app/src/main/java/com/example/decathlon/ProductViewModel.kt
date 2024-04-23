package com.example.decathlon

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** [ViewModel] to fetch product details and perform operations on the product list. */
class ProductViewModel : ViewModel() {

    private val _productFetchResult = MutableLiveData<ProductFetchResult>()

    /** Public variable exposing result of API call. */
    val productFetchResult = _productFetchResult

    /** Method to fetch product details. */
    fun fetchProductDetails() {
        // Note: Using a sample URL here
        val retrofitBuilder = Retrofit.Builder().baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val productService = retrofitBuilder.create(ProductService::class.java)

        productService.getProducts().enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(
                call: Call<List<ProductModel>>, response: Response<List<ProductModel>>
            ) {
                if (response.isSuccessful) {
                    if (!response.body().isNullOrEmpty()) {
                        // Note: We would be posting the API response body i.e. the fetched product list here.
                        // _productFetchResult.postValue(ProductFetchResult.Success(response.body()))

                        // Since there is no actual API endpoint currently, posting manual data
                        _productFetchResult.postValue(ProductFetchResult.Success(getProductDetails()))
                    } else {
                        Log.d(TAG, "Response for fetching of product details is null or empty.")
                        _productFetchResult.postValue(ProductFetchResult.Failure)
                    }
                } else {
                    Log.d(TAG, "Error while fetching product details.")
                    _productFetchResult.postValue(ProductFetchResult.Failure)
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, throwable: Throwable) {
                Log.d(TAG, "Fetching of product details failed.")
                _productFetchResult.postValue(ProductFetchResult.Failure)
            }
        })

        // Note: Posting a success result to facilitate not using actual API here
        _productFetchResult.postValue(ProductFetchResult.Success(getProductDetails()))
    }

    /**
     * Method to search in the product list as per search query.
     *
     * @param productList the list of products to search from
     * @param searchQuery the query to be searched in the list
     *
     * @return list of products which match the search query
    */
    fun performSearch(productList: List<ProductModel>, searchQuery: Editable?): List<ProductModel> {
        return productList.filter {
            it.name.contains(searchQuery.toString(), ignoreCase = true) ||
                    it.brand.contains(searchQuery.toString(), ignoreCase = true)
        }
    }

    /**
     * Method to sort product list by prices.
     *
     * @param productList the list of products to be sorted
     *
     * @return list of products sorted by prices
     */
    fun sortByPrices(productList: List<ProductModel>) = productList.sortedBy { it.price }

    /**
     * Method to sort product list by names.
     *
     * @param productList the list of products to be sorted
     *
     * @return list of products sorted by names
     */
    fun sortByNames(productList: List<ProductModel>) = productList.sortedBy { it.name }

    private fun getProductDetails() = listOf(
        ProductModel(id = "1", name = "Cotton T-Shirt for boys", brand = "Domyos", price = 50f),
        ProductModel(id = "2", name = "Cotton T-Shirt for girls", brand = "Domyos", price = 50f),
        ProductModel(id = "3", name = "Badminton racket 100", brand = "Perfly", price = 800f),
        ProductModel(id = "4", name = "Badminton racket 250", brand = "Perfly", price = 1000f),
        ProductModel(id = "5", name = "Kids Cycle (4-6 years)", brand = "Btwin", price = 100f),
        ProductModel(id = "6", name = "Kids Cycle (6-8 years)", brand = "Btwin", price = 120f),
        ProductModel(id = "7", name = "Kids Cycle (8-10 years)", brand = "Btwin", price = 150f),
        ProductModel(id = "8", name = "Football Jersey", brand = "Domyos", price = 50f),
        ProductModel(id = "9", name = "Cricket Jersey", brand = "Domyos", price = 50f),
        ProductModel(id = "10", name = "Cricket Jersey", brand = "Artengo", price = 80f),
        ProductModel(id = "11", name = "Football Jersey", brand = "Kipsta", price = 90f),
        ProductModel(id = "12", name = "Cricket Jersey", brand = "Kipsta", price = 60f),
        ProductModel(id = "13", name = "Cricket Jersey", brand = "Decathlon", price = 50f),
        ProductModel(id = "14", name = "Football Jersey", brand = "Artengo", price = 80f),
        ProductModel(id = "15", name = "Trousers Boys", brand = "Quecha", price = 40f),
        ProductModel(id = "16", name = "Shoes - Boys - Size 7", brand = "Artengo", price = 300f),
        ProductModel(id = "17", name = "Bag Size 10", brand = "Quecha", price = 100f),
        ProductModel(id = "18", name = "Shoes - Boys - Size 10", brand = "Kipsta", price = 450f),
        ProductModel(id = "19", name = "Trousers Girls", brand = "Quecha", price = 40f),
        ProductModel(id = "20", name = "Shoes - Girls - Size 4", brand = "Domyos", price = 250f),
        ProductModel(id = "21", name = "Cricket Bat", brand = "Decathlon", price = 80f),
        ProductModel(id = "22", name = "Bag Size 20", brand = "Quecha", price = 150f),
        ProductModel(id = "23", name = "Swimming glasses", brand = "Decathlon", price = 20f),
        ProductModel(id = "24", name = "Shoes - Girls - Size 6", brand = "Decathlon", price = 90f),
        ProductModel(id = "25", name = "Shoes - Boys - Size 12", brand = "Quecha", price = 120f),
        ProductModel(id = "26", name = "Roller skates", brand = "Decathlon", price = 200f),
        ProductModel(id = "27", name = "Shoes - Girls - Size 8", brand = "Quecha", price = 150f),
        ProductModel(id = "28", name = "Water Bottle", brand = "Btwin", price = 100f),
        ProductModel(
            id = "29",
            name = "Shoes - Girls - Size 10",
            brand = "Decathlon",
            price = 200f
        ),
        ProductModel(id = "30", name = "Trampoline", brand = "Domyos", price = 200f)
    )

    private companion object {
        const val TAG = "ProductViewModel"
    }
}
