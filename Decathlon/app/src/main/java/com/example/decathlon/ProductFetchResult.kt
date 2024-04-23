package com.example.decathlon

/** Sealed class to store result of API call to fetch product details. */
sealed class ProductFetchResult {

    /** To be used when API fetch is successful. */
    class Success(val productList: List<ProductModel>) : ProductFetchResult()

    /** To be used when API fetch fails. */
    data object Failure : ProductFetchResult()
}
