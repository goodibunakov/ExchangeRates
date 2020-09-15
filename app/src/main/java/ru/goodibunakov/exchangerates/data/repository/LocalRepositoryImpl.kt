package ru.goodibunakov.exchangerates.data.repository

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import ru.goodibunakov.exchangerates.domain.LocalRepository
import ru.goodibunakov.exchangerates.presentation.model.CurrencySaved
import ru.goodibunakov.exchangerates.presentation.model.CurrencyUi

class LocalRepositoryImpl(preferences: SharedPreferences) : LocalRepository {

    private val prefSubject = BehaviorSubject.createDefault(preferences)

    private val prefChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, _ ->
            prefSubject.onNext(sharedPreferences)
        }

    init {
        preferences.registerOnSharedPreferenceChangeListener(prefChangeListener)
    }

    override fun save(item: CurrencyUi): Completable =
        prefSubject
            .firstOrError()
            .editSharedPreferences {
                putString(KEY_CHAR_CODE, item.charCode)
                putInt(KEY_NOMINAL, item.nominal)
                putFloat(KEY_VALUE, item.value.toFloat())
            }

    override fun load(): Observable<CurrencySaved?> =
        prefSubject
            .map {
                it.getString(KEY_CHAR_CODE, "")?.let { charCode ->
                    CurrencySaved(
                        charCode,
                        it.getInt(KEY_NOMINAL, 0),
                        it.getFloat(KEY_VALUE, 0F)
                    )
                }
            }

    companion object {

        fun create(context: Context): LocalRepositoryImpl {
            val preferences = context.getSharedPreferences("currency", Context.MODE_PRIVATE)
            return LocalRepositoryImpl(preferences)
        }

        private const val KEY_CHAR_CODE = "key_char_code"
        private const val KEY_NOMINAL = "key_nominal"
        private const val KEY_VALUE = "key_value"

    }

    private fun Single<SharedPreferences>.editSharedPreferences(batch: SharedPreferences.Editor.() -> Unit): Completable =
        flatMapCompletable {
            Completable.fromAction {
                it.edit().also(batch).apply()
            }
        }
}