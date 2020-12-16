package com.pankaj.timeline.di

import android.content.Context
import androidx.room.Room
import com.pankaj.timeline.db.AppDatabase
import com.pankaj.timeline.ui.repository.MainRepository
import com.pankaj.timeline.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {
    single { MainRepository(get(),get()) }
    viewModel { MainViewModel(get()) }
    single {
        return@single Room.databaseBuilder(
        get() as Context,
        AppDatabase::class.java, "post_db"
    ).build()
    }

}
