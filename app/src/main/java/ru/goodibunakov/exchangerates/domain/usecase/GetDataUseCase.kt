package ru.goodibunakov.exchangerates.domain.usecase

import io.reactivex.Single
import ru.goodibunakov.exchangerates.data.mapper.ToCurrencyEntityMapper
import ru.goodibunakov.exchangerates.domain.ApiRepository
import ru.goodibunakov.exchangerates.domain.model.CurrencyEntity

class GetDataUseCase(private val apiRepository: ApiRepository) {

    fun data(): Single<out List<CurrencyEntity>> {
        return apiRepository.getCurrency()
            .map { ToCurrencyEntityMapper.map(it) }
    }
}