package ru.goodibunakov.exchangerates.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.goodibunakov.exchangerates.data.database.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_CURRENCY)
data class CurrencyDTO(
    @SerializedName("CharCode")
    val charCode: String,
    @PrimaryKey
    @SerializedName("ID")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("Previous")
    val previous: Double,
    @SerializedName("Value")
    val value: Double
)