package com.toharifqi.myfavoritesports.core.domain.usecase

import com.toharifqi.myfavoritesports.core.data.Resource
import com.toharifqi.myfavoritesports.core.domain.model.Sport
import kotlinx.coroutines.flow.Flow

interface SportUseCase {
    fun getAllSports(): Flow<Resource<List<Sport>>>
    fun getFavoriteSport(): Flow<List<Sport>>
    fun setFavoriteSport(sport: Sport, state: Boolean)
}