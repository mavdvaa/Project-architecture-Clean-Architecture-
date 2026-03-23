package com.example.cleanarchitecture

class GetFactsUseCase(private val repository: CatRepository) {
    suspend operator fun invoke(limit: Int): List<CatFact> {
        return repository.getFacts(limit)
    }
}
