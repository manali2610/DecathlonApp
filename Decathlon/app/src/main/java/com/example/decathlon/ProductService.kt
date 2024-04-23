package com.example.decathlon

import retrofit2.Call
import retrofit2.http.GET

/** Interface to fetch product details. */
interface ProductService {

    /** Method to fetch product details. */
    @GET("products") fun getProducts(): Call<List<ProductModel>>
}
