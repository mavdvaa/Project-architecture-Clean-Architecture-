package com.example.cleanarchitecture

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FactsViewModel(private val getFactsUseCase: GetFactsUseCase) : ViewModel() {
    var facts by mutableStateOf<List<CatFact>>(emptyList())
    var countText by mutableStateOf("5")
    var isLoading by mutableStateOf(false)

    fun loadFacts() {
        val limit = countText.toIntOrNull() ?: 1
        viewModelScope.launch {
            isLoading = true
            try {
                facts = getFactsUseCase(limit)
            } catch (e: Exception) {
            } finally {
                isLoading = false
            }
        }
    }
}
