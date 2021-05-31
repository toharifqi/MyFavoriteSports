package com.toharifqi.myfavoritesports.core.data

import com.toharifqi.myfavoritesports.core.data.source.local.LocalDataSource
import com.toharifqi.myfavoritesports.core.data.source.remote.RemoteDataSource
import com.toharifqi.myfavoritesports.core.data.source.remote.network.ApiResponse
import com.toharifqi.myfavoritesports.core.data.source.remote.response.SportsItemResponse
import com.toharifqi.myfavoritesports.core.domain.model.Sport
import com.toharifqi.myfavoritesports.core.domain.repository.ISportRepository
import com.toharifqi.myfavoritesports.core.utils.AppExecutors
import com.toharifqi.myfavoritesports.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SportRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutors,
): ISportRepository {
    override fun getAllSports(): Flow<Resource<List<Sport>>> =
        object : NetworkBoundResource<List<Sport>, List<SportsItemResponse>>() {
            override fun loadFromDB(): Flow<List<Sport>> {
                return localDataSource.getAllSports().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Sport>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SportsItemResponse>>> =
                remoteDataSource.getAllSports()

            override suspend fun saveCallResult(data: List<SportsItemResponse>) {
                val sportList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSports(sportList)
            }

        }.asFlow()

    override fun getFavoriteSports(): Flow<List<Sport>> {
        return localDataSource.getFavoriteSports().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteSport(sport: Sport, state: Boolean) {
        val sportEntity = DataMapper.mapDomainToEntity(sport)
        appExecutor.diskIO().execute { localDataSource.setFavoriteSport(sportEntity, state) }
    }
}