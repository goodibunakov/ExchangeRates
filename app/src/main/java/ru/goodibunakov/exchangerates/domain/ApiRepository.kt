package ru.goodibunakov.exchangerates.domain

import io.reactivex.Single
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO

interface ApiRepository {

    fun getCurrency(): Single<List<CurrencyDTO>>
}