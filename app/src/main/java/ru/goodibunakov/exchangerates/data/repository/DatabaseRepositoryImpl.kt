package ru.goodibunakov.exchangerates.data.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import ru.goodibunakov.exchangerates.data.database.Dao
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO
import ru.goodibunakov.exchangerates.domain.DatabaseRepository

class DatabaseRepositoryImpl(private val dao: Dao) : DatabaseRepository {

    override fun insert(currencyList: List<CurrencyDTO>): Completable {
        return dao.insert(currencyList)
    }

    override fun getCurrency(): Observable<List<CurrencyDTO>> {
        return dao.getCurrency()
            .doOnError { Log.d("debug", "DatabaseRepositoryImpl getCurrency $it") }
            .doOnComplete { Log.d("debug", "DatabaseRepositoryImpl getCurrency doOnComplete ") }
            .doOnNext { Log.d("debug", "DatabaseRepositoryImpl getCurrency doOnNext $it ") }
    }

    override fun update(currencyList: List<CurrencyDTO>): Completable {
        return dao.update(currencyList)
    }
}