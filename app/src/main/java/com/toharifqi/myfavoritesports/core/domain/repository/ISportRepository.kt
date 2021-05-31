package com.toharifqi.myfavoritesports.core.domain.repository

import com.toharifqi.myfavoritesports.core.data.Resource
import com.toharifqi.myfavoritesports.core.domain.model.Sport
import kotlinx.coroutines.flow.Flow

interface ISportRepository {
    fun getAllSports(): Flow<Resource<List<Sport>>>
    fun getFavoriteSports(): Flow<List<Sport>>
    fun setFavoriteSport(sport: Sport, state: Boolean)
}