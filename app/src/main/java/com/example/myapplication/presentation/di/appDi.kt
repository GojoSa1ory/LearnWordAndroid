package com.example.myapplication.presentation.di

import com.example.domain.usecase.language.CreateLanguageUseCase
import com.example.domain.usecase.language.DeleteLanguageUseCase
import com.example.domain.usecase.language.GetLanguageAndWordsByIdUseCase
import com.example.domain.usecase.language.GetLanguageUseCase
import com.example.domain.usecase.word.CreateWordUseCase
import com.example.domain.usecase.word.DeleteWordUseCase
import com.example.domain.usecase.word.GetWordWithLanguageUseCase
import com.example.domain.usecase.word.GetWordsByLangIdUseCase
import com.example.domain.usecase.word.SearchWordUseCase
import org.koin.dsl.module

val appModule = module {

    factory { GetWordWithLanguageUseCase(get()) }
    factory { DeleteWordUseCase(get()) }
    factory { SearchWordUseCase(get()) }
    factory { CreateWordUseCase(get()) }
    factory { GetWordsByLangIdUseCase(get()) }

    factory { GetLanguageUseCase(get()) }
    factory { CreateLanguageUseCase(get()) }
    factory { GetLanguageAndWordsByIdUseCase(get()) }
    factory { DeleteLanguageUseCase(get()) }
//    single {
//        Room.databaseBuilder(get(), AppDatabase::class.java, "learnWord-database")
//            .build()
//    }
//
//    single {
//        val db = get<AppDatabase>()
//        db.wordDao()
//    }
//
//    single {
//        get<AppDatabase>().languageDao()
//    }
//
//    single<WordRepository> {
//        WordRepositoryImpl(get())
//    }
//
//    single<LanguageRepository> {
//        LanguageRepositoryImpl(get())
//    }
//
//}
//
//val domainModule = module {
//
//    //Words use cases
//    factory { GetWordsUseCase(get()) }
//    factory { CreateWordUseCase(get()) }
//    factory { UpdateWordUseCase(get()) }
//    factory { DeleteWordUseCase(get()) }
//    factory { SearchWordUseCase(get()) }
//
//    //Language use cases
//    factory { GetLanguagesWithWordsUseCase(get()) }
//    factory { GetLanguagesUseCase(get()) }
//    factory { GetLanguageWithWordsByIdUseCase(get()) }
//    factory { CreateLanguageUseCase(get()) }
//    factory { SearchLanguageUseCase(get()) }
//}
//
//
//val appDi = module {
//
//    //Home screen
//    viewModel { HomeScreenViewModel(get(), get(), get()) }
//    viewModel { CreateWordScreenViewModel(get(), get()) }
//
//    //Language screen
//    viewModel { com.example.language.ui.screen.main.LanguageScreenViewModel(get(), get()) }
//    viewModel { LanguageDetailsScreenViewModel(get()) }
//    viewModel {
//        com.example.language.ui.component.languagebottomsheet.LanguageBottomSheetViewModel(
//            get()
//        )
//    }
}