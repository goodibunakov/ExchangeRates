package ru.goodibunakov.exchangerates.data.dto

import com.google.gson.annotations.SerializedName
import ru.goodibunakov.exchangerates.domain.Currency

data class CurrencyDTO(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valute: Map<String, Currency>
)