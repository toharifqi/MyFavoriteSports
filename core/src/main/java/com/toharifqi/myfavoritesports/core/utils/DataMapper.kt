package com.toharifqi.myfavoritesports.core.utils

import com.toharifqi.myfavoritesports.core.data.source.local.entity.SportEntity
import com.toharifqi.myfavoritesports.core.data.source.remote.response.SportsItemResponse
import com.toharifqi.myfavoritesports.core.domain.model.Sport

object DataMapper {
    fun mapResponsesToEntities(input: List<SportsItemResponse>): List<SportEntity>{
        val sportList = ArrayList<SportEntity>()
        input.map {
            val sport = SportEntity(
                id = it.idSport,
                format = it.strFormat,
                name = it.strSport,
                thumb = it.strSportThumb,
                thumbGreen = it.strSportThumbGreen,
                description = it.strSportDescription,
                isFavorite = false
            )
            sportList.add(sport)
        }
        return sportList
    }

    fun mapEntitiesToDomain(input: List<SportEntity>): List<Sport> =
        input.map {
            Sport(
                sportId = it.id,
                format = it.format,
                name = it.name,
                thumb = it.thumb,
                thumbGreen = it.thumbGreen,
                description = it.description,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Sport) = SportEntity(
        id = input.sportId,
        format = input.format,
        name = input.name,
        thumb = input.thumb,
        thumbGreen = input.thumbGreen,
        description = input.description,
        isFavorite = input.isFavorite
    )
}