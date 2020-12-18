package com.pankaj.timeline.di

import android.content.Context
import androidx.room.Room
import com.pankaj.timeline.db.AppDatabase
import com.pankaj.timeline.ui.repository.DetailRepository
import com.pankaj.timeline.ui.repository.MainRepository
import com.pankaj.timeline.viewmodel.DetailViewModel
import com.pankaj.timeline.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {
    single { MainRepository(get(),get()) }
    single { DetailRepository(get(),get()) }
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    single {
        return@single Room.databaseBuilder(
        get() as Context,
        AppDatabase::class.java, "post_db"
    ).build()
    }

}
