package ru.goodibunakov.exchangerates.presentation

import ru.goodibunakov.exchangerates.domain.Mapper
import ru.goodibunakov.exchangerates.domain.model.CurrencyEntity
import ru.goodibunakov.exchangerates.presentation.model.CurrencyUi

object ToCurrencyUiMapper : Mapper<List<CurrencyEntity>, List<CurrencyUi>> {

    override fun map(from: List<CurrencyEntity>): List<CurrencyUi> {
        return from.map {
            CurrencyUi(
                charCode = it.charCode,
                id = it.id,
                name = it.name,
                nominal = it.nominal,
                numCode = it.numCode,
                value = it.value
            )
        }
    }
}