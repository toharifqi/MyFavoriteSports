package com.toharifqi.myfavoritesports.core.domain.usecase

import com.toharifqi.myfavoritesports.core.data.Resource
import com.toharifqi.myfavoritesports.core.domain.model.Sport
import com.toharifqi.myfavoritesports.core.domain.repository.ISportRepository
import kotlinx.coroutines.flow.Flow

class SportInteractor(private val sportRepository: ISportRepository): SportUseCase {
    override fun getAllSports(): Flow<Resource<List<Sport>>> = sportRepository.getAllSports()

    override fun getFavoriteSport(): Flow<List<Sport>> = sportRepository.getFavoriteSports()

    override fun setFavoriteSport(sport: Sport, state: Boolean) = sportRepository.setFavoriteSport(sport, state)

}