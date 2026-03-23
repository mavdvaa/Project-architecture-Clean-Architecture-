package com.example.cleanarchitecture

import retrofit2.http.GET
import retrofit2.http.Query

data class CatFactDto(val fact: String)
data class CatResponse(val data: List<CatFactDto>) // Убедитесь, что здесь CatResponse

interface CatApi {
    @GET("facts")
    suspend fun getFacts(@Query("limit") limit: Int): CatResponse
}

