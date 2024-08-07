package com.example.myapplication.presentation.di

import androidx.room.Room
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.repositories.WordRepositoryImpl
import com.example.myapplication.domain.repositories.WordRepository
import com.example.myapplication.domain.useсase.wordusecase.CreateWordUseCase
import com.example.myapplication.domain.useсase.wordusecase.DeleteWordUseCase
import com.example.myapplication.domain.useсase.wordusecase.GetWordsUseCase
import com.example.myapplication.domain.useсase.wordusecase.SearchWordUseCase
import com.example.myapplication.domain.useсase.wordusecase.UpdateWordUseCase
import com.example.myapplication.presentation.screen.home.HomeScreenViewModel
import com.example.myapplication.presentation.screen.home.components.homeBottomSheet.HomeBottomSheetViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "learnWord-database")
            .build()
    }

    single {
        val db = get<AppDatabase>()
        db.wordDao()
    }

    single<WordRepository> {
        WordRepositoryImpl(get())
    }

}

val domainModule = module {
    factory { GetWordsUseCase(get()) }
    factory { CreateWordUseCase(get()) }
    factory { UpdateWordUseCase(get()) }
    factory { DeleteWordUseCase(get()) }
    factory { SearchWordUseCase(get()) }
}


val appDi = module {
    viewModel { HomeScreenViewModel(get(), get(), get()) }
    viewModel { HomeBottomSheetViewModel(get()) }
}