package ru.goodibunakov.exchangerates.domain

import io.reactivex.Completable
import io.reactivex.Observable
import ru.goodibunakov.exchangerates.presentation.model.CurrencySaved
import ru.goodibunakov.exchangerates.presentation.model.CurrencyUi

interface LocalRepository {

    fun save(item: CurrencyUi): Completable

    fun load(): Observable<CurrencySaved?>
}