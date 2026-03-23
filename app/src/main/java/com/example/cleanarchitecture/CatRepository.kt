package com.example.cleanarchitecture

interface CatRepository {
    suspend fun getFacts(limit: Int): List<CatFact>
}
