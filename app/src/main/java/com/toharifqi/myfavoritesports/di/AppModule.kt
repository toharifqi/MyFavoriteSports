package com.toharifqi.myfavoritesports.di

import com.toharifqi.myfavoritesports.core.domain.usecase.SportInteractor
import com.toharifqi.myfavoritesports.core.domain.usecase.SportUseCase
import com.toharifqi.myfavoritesports.ui.detail.DetailViewModel
import com.toharifqi.myfavoritesports.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory <SportUseCase> { SportInteractor(get()) }
}

val viewModeModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}