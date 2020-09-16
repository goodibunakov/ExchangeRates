package ru.goodibunakov.exchangerates.domain

import io.reactivex.Completable
import io.reactivex.Observable
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO

interface DatabaseRepository {

    fun insert(currencyList: List<CurrencyDTO>): Completable

    fun getCurrency(): Observable<List<CurrencyDTO>>

    fun update(currencyList: List<CurrencyDTO>): Completable

}