package ru.goodibunakov.exchangerates.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.goodibunakov.exchangerates.data.database.DatabaseConstants.DATABASE_NAME
import ru.goodibunakov.exchangerates.data.database.DatabaseConstants.DATABASE_VERSION
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO

@Database(entities = [CurrencyDTO::class], version = DATABASE_VERSION, exportSchema = true)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context): CurrencyDatabase  =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CurrencyDatabase::class.java, DATABASE_NAME)
                .build()
    }
}