package ru.goodibunakov.exchangerates

import android.app.Application
import ru.goodibunakov.exchangerates.data.ApiRepositoryImpl
import ru.goodibunakov.exchangerates.data.ApiService
import ru.goodibunakov.exchangerates.domain.ApiRepository
import ru.goodibunakov.exchangerates.domain.GetDataUseCase
import ru.goodibunakov.exchangerates.presentation.ViewModelFactory

class App: Application() {

    companion object {
        lateinit var apiRepository: ApiRepository
//        lateinit var databaseRepository: DatabaseRepository
        lateinit var viewModelFactory: ViewModelFactory
    }

    override fun onCreate() {
        super.onCreate()

        apiRepository = ApiRepositoryImpl(ApiService.create())
//        databaseRepository = DatabaseRepositoryImpl(Database.getDatabase(this).dao(), applicationContext)
        viewModelFactory = ViewModelFactory(
            GetDataUseCase(apiRepository)
        )
    }
}