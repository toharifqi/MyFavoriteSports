package com.toharifqi.myfavoritesports.core.data.source.local

import com.toharifqi.myfavoritesports.core.data.source.local.entity.SportEntity
import com.toharifqi.myfavoritesports.core.data.source.local.room.SportDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val sportDao: SportDao) {
    fun getAllSports(): Flow<List<SportEntity>> = sportDao.getAllSports()

    fun getFavoriteSports(): Flow<List<SportEntity>> = sportDao.getFavoriteSport()

    suspend fun insertSports(sportsList: List<SportEntity>) = sportDao.insertSports(sportsList)

    fun setFavoriteSport(sport: SportEntity, newState: Boolean){
        sport.isFavorite = newState
        sportDao.updateFavoriteSport(sport)
    }
}