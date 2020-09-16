package ru.goodibunakov.exchangerates.domain

import io.reactivex.Observable
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO

interface ApiRepository {

    fun getCurrency(): Observable<List<CurrencyDTO>>
}