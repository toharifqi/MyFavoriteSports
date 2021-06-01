package com.toharifqi.myfavoritesports.ui.detail

import androidx.lifecycle.ViewModel
import com.toharifqi.myfavoritesports.core.domain.model.Sport
import com.toharifqi.myfavoritesports.core.domain.usecase.SportUseCase

class DetailViewModel(private val sportUseCase: SportUseCase): ViewModel() {
    fun setFavoriteSport(sport: Sport, newState: Boolean){
        sportUseCase.setFavoriteSport(sport, newState)
    }
}