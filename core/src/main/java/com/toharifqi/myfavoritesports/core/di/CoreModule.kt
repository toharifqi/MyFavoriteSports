package com.toharifqi.myfavoritesports.core.di

import androidx.room.Room
import com.toharifqi.myfavoritesports.core.data.SportRepository
import com.toharifqi.myfavoritesports.core.data.source.local.LocalDataSource
import com.toharifqi.myfavoritesports.core.data.source.local.room.SportDatabase
import com.toharifqi.myfavoritesports.core.data.source.remote.RemoteDataSource
import com.toharifqi.myfavoritesports.core.data.source.remote.network.ApiService
import com.toharifqi.myfavoritesports.core.domain.repository.ISportRepository
import com.toharifqi.myfavoritesports.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<SportDatabase>().sportDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            SportDatabase::class.java, "Sport.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single <ISportRepository> { SportRepository(get(), get(), get()) }
}