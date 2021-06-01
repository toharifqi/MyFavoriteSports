package com.toharifqi.myfavoritesports.core.data.source.remote.network

import com.toharifqi.myfavoritesports.core.data.source.remote.response.SportsListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("all_sports.php")
    suspend fun getList(): SportsListResponse
}