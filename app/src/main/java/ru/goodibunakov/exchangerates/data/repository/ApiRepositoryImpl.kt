package ru.goodibunakov.exchangerates.data.repository

import io.reactivex.Observable
import ru.goodibunakov.exchangerates.data.ApiService
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO
import ru.goodibunakov.exchangerates.domain.ApiRepository

class ApiRepositoryImpl(
    private val apiService: ApiService
) : ApiRepository {

    override fun getCurrency(): Observable<List<CurrencyDTO>> {
        return apiService.getData()
            .map { it.valute.values.toMutableList() }
    }
}