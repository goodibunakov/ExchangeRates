package ru.goodibunakov.exchangerates.presentation.model

data class CurrencyUi(
    val charCode: String,
    val id: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val value: Double
)