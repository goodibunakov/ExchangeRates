package ru.goodibunakov.exchangerates

import android.app.Application
import ru.goodibunakov.exchangerates.data.ApiService
import ru.goodibunakov.exchangerates.data.repository.ApiRepositoryImpl
import ru.goodibunakov.exchangerates.data.repository.LocalRepositoryImpl
import ru.goodibunakov.exchangerates.domain.ApiRepository
import ru.goodibunakov.exchangerates.domain.LocalRepository
import ru.goodibunakov.exchangerates.domain.usecase.GetDataUseCase
import ru.goodibunakov.exchangerates.presentation.viewmodel.ViewModelFactory

class App: Application() {

    companion object {
        lateinit var apiRepository: ApiRepository
//        lateinit var databaseRepository: DatabaseRepository
        lateinit var viewModelFactory: ViewModelFactory
        lateinit var localRepository: LocalRepository
    }

    override fun onCreate() {
        super.onCreate()

        apiRepository = ApiRepositoryImpl(ApiService.create())
        localRepository = LocalRepositoryImpl.create(applicationContext)
//        databaseRepository = DatabaseRepositoryImpl(Database.getDatabase(this).dao(), applicationContext)
        viewModelFactory = ViewModelFactory(
            GetDataUseCase(apiRepository), localRepository
        )
    }
}