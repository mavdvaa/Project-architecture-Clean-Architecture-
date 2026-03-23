package com.example.cleanarchitecture

import com.example.cleanarchitecture.CatFact
import com.example.cleanarchitecture.CatRepository

class CatRepositoryImpl(private val api: CatApi) : CatRepository {
    override suspend fun getFacts(limit: Int): List<CatFact> {
        return api.getFacts(limit).data.map { dto ->
            CatFact(text = dto.fact)
        }
    }
}
