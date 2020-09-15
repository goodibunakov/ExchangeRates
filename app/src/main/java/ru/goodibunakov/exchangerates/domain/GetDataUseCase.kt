package ru.goodibunakov.exchangerates.domain

import io.reactivex.Single

class GetDataUseCase(private val apiRepository: ApiRepository) {

    fun data(): Single<out List<Currency>> {
        return apiRepository.getCurrency()
    }
}