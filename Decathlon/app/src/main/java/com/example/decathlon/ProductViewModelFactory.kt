package com.example.decathlon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/** [ViewModelProvider.Factory] for [ProductViewModel]. */
@Suppress("UNCHECKED_CAST")
class ProductViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel() as T
    }
}
