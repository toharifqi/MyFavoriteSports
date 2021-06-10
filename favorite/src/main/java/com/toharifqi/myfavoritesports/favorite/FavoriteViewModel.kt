package com.toharifqi.myfavoritesports.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.toharifqi.myfavoritesports.core.domain.usecase.SportUseCase

class FavoriteViewModel(sportUseCase: SportUseCase) : ViewModel() {
    val favoriteSports = sportUseCase.getFavoriteSport().asLiveData()
}