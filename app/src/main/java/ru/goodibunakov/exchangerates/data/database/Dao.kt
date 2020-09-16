package ru.goodibunakov.exchangerates.data.database

import androidx.room.*
import androidx.room.Dao
import io.reactivex.Completable
import io.reactivex.Observable
import ru.goodibunakov.exchangerates.data.database.DatabaseConstants.TABLE_CURRENCY
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyList: List<CurrencyDTO>): Completable

    @Update
    fun update(currencyList: List<CurrencyDTO>): Completable

    @Query("SELECT * FROM $TABLE_CURRENCY")
    fun getCurrency(): Observable<List<CurrencyDTO>>
}