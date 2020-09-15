package ru.goodibunakov.exchangerates.domain.model

data class CurrencyEntity(
    val charCode: String,
    val id: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val value: Double
)