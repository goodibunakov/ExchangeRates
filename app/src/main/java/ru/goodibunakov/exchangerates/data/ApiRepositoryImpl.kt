package ru.goodibunakov.exchangerates.data

import io.reactivex.Single
import ru.goodibunakov.exchangerates.domain.ApiRepository
import ru.goodibunakov.exchangerates.domain.Currency

class ApiRepositoryImpl(
    private val apiService: ApiService
) : ApiRepository {

    override fun getCurrency(): Single<List<Currency>> {
        return apiService.getData()
            .map { it.valute.values.toMutableList() }
    }
}