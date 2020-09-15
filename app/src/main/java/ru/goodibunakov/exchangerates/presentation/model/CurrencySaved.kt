package ru.goodibunakov.exchangerates.presentation.model

data class CurrencySaved (
    val charCode: String,
    val nominal: Int,
    val value: Float
)