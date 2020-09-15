package ru.goodibunakov.exchangerates.data.mapper

import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO
import ru.goodibunakov.exchangerates.domain.Mapper
import ru.goodibunakov.exchangerates.domain.model.CurrencyEntity

object ToCurrencyEntityMapper : Mapper<List<CurrencyDTO>, List<CurrencyEntity>> {

    override fun map(from: List<CurrencyDTO>): List<CurrencyEntity> {
        return from.map {
            CurrencyEntity(
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