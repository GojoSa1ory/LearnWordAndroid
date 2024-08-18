package com.example.myapplication.presentation.di

import androidx.room.Room
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.repositories.LanguageRepositoryImpl
import com.example.myapplication.data.repositories.WordRepositoryImpl
import com.example.myapplication.domain.repositories.LanguageRepository
import com.example.myapplication.domain.repositories.WordRepository
import com.example.myapplication.domain.useсase.language.CreateLanguageUseCase
import com.example.myapplication.domain.useсase.language.GetLanguageWithWordsByIdUseCase
import com.example.myapplication.domain.useсase.language.GetLanguagesUseCase
import com.example.myapplication.domain.useсase.language.GetLanguagesWithWordsUseCase
import com.example.myapplication.domain.useсase.language.SearchLanguageUseCase
import com.example.myapplication.domain.useсase.word.CreateWordUseCase
import com.example.myapplication.domain.useсase.word.DeleteWordUseCase
import com.example.myapplication.domain.useсase.word.GetWordsUseCase
import com.example.myapplication.domain.useсase.word.SearchWordUseCase
import com.example.myapplication.domain.useсase.word.UpdateWordUseCase
import com.example.myapplication.presentation.screen.home.HomeScreenViewModel
import com.example.myapplication.presentation.screen.home.createword.CreateWordScreenViewModel
import com.example.myapplication.presentation.screen.language.LanguageScreenViewModel
import com.example.myapplication.presentation.screen.language.component.languagebottomsheet.LanguageBottomSheetViewModel
import com.example.myapplication.presentation.screen.language.details.LanguageDetailsScreenViewModel
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

    single {
        get<AppDatabase>().languageDao()
    }

    single<WordRepository> {
        WordRepositoryImpl(get())
    }

    single<LanguageRepository> {
        LanguageRepositoryImpl(get())
    }

}

val domainModule = module {

    //Words use cases
    factory { GetWordsUseCase(get()) }
    factory { CreateWordUseCase(get()) }
    factory { UpdateWordUseCase(get()) }
    factory { DeleteWordUseCase(get()) }
    factory { SearchWordUseCase(get()) }

    //Language use cases
    factory { GetLanguagesWithWordsUseCase(get()) }
    factory { GetLanguagesUseCase(get()) }
    factory { GetLanguageWithWordsByIdUseCase(get()) }
    factory { CreateLanguageUseCase(get()) }
    factory { SearchLanguageUseCase(get()) }
}


val appDi = module {

    //Home screen
    viewModel { HomeScreenViewModel(get(), get(), get()) }
    viewModel { CreateWordScreenViewModel(get(), get()) }

    //Language screen
    viewModel { LanguageScreenViewModel(get(), get()) }
    viewModel { LanguageDetailsScreenViewModel(get()) }
    viewModel { LanguageBottomSheetViewModel(get()) }
}