package com.toharifqi.myfavoritesports.favorite.di

import com.toharifqi.myfavoritesports.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}