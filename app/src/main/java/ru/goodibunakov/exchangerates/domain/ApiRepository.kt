package ru.goodibunakov.exchangerates.domain

import io.reactivex.Single

interface ApiRepository {

    fun getCurrency(): Single<List<Currency>>
}