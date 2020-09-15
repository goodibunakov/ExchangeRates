package ru.goodibunakov.exchangerates.data.repository

import io.reactivex.Single
import ru.goodibunakov.exchangerates.data.ApiService
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO
import ru.goodibunakov.exchangerates.domain.ApiRepository

class ApiRepositoryImpl(
    private val apiService: ApiService
) : ApiRepository {

    override fun getCurrency(): Single<List<CurrencyDTO>> {
        return apiService.getData()
            .map { it.valute.values.toMutableList() }
    }
}