package com.toharifqi.myfavoritesports.core.data.source.local.room

import androidx.room.*
import com.toharifqi.myfavoritesports.core.data.source.local.entity.SportEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SportDao {
    @Query("SELECT * FROM sports")
    fun getAllSports(): Flow<List<SportEntity>>

    @Query("SELECT * FROM sports where isFavorite = 1")
    fun getFavoriteSport(): Flow<List<SportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSports(tourism: List<SportEntity>)

    @Update
    fun updateFavoriteSport(tourism: SportEntity)
}