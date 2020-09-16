package ru.goodibunakov.exchangerates

import android.app.Application
import com.facebook.stetho.Stetho
import ru.goodibunakov.exchangerates.data.ApiService
import ru.goodibunakov.exchangerates.data.database.CurrencyDatabase
import ru.goodibunakov.exchangerates.data.repository.ApiRepositoryImpl
import ru.goodibunakov.exchangerates.data.repository.DatabaseRepositoryImpl
import ru.goodibunakov.exchangerates.data.repository.LocalRepositoryImpl
import ru.goodibunakov.exchangerates.domain.ApiRepository
import ru.goodibunakov.exchangerates.domain.DatabaseRepository
import ru.goodibunakov.exchangerates.domain.LocalRepository
import ru.goodibunakov.exchangerates.domain.usecase.GetDataUseCase
import ru.goodibunakov.exchangerates.presentation.viewmodel.ViewModelFactory

class App: Application() {

    companion object {
        lateinit var apiRepository: ApiRepository
        lateinit var databaseRepository: DatabaseRepository
        lateinit var localRepository: LocalRepository
        lateinit var viewModelFactory: ViewModelFactory
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        apiRepository = ApiRepositoryImpl(ApiService.create())
        databaseRepository = DatabaseRepositoryImpl(CurrencyDatabase.getDatabase(this).dao())
        localRepository = LocalRepositoryImpl.create(applicationContext)
        viewModelFactory = ViewModelFactory(
            GetDataUseCase(apiRepository, databaseRepository),
            localRepository
        )
    }
}