package ru.goodibunakov.exchangerates.domain.usecase

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import ru.goodibunakov.exchangerates.data.dto.CurrencyDTO
import ru.goodibunakov.exchangerates.data.mapper.ToCurrencyEntityMapper
import ru.goodibunakov.exchangerates.domain.ApiRepository
import ru.goodibunakov.exchangerates.domain.DatabaseRepository
import ru.goodibunakov.exchangerates.domain.model.CurrencyEntity
import java.util.concurrent.TimeUnit

class GetDataUseCase(
    private val apiRepository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) {

    fun data(): Observable<out List<CurrencyEntity>> {
        return Observable.concat(
            loadFromDb(),
            loadFromApiSaveToDb()
        )
            .filter { it.isNotEmpty() }
            .firstOrError()
            .doOnError { Log.d("debug", "error $it") }
            .map { ToCurrencyEntityMapper.map(it) }
            .toObservable()
    }

    private fun loadFromDb(): Observable<List<CurrencyDTO>> {
        return databaseRepository.getCurrency().firstOrError().toObservable()
    }

    private fun loadFromApiSaveToDb(): Observable<out List<CurrencyDTO>> {
        return apiRepository.getCurrency()
            .doOnNext { list ->
                databaseRepository.insert(list)
                    .subscribe({
                        Log.d("debug", "сохранил в БД")
                    }, {
                        Log.d("debug", "при сохранении в БД ошибка ${it.message}")
                    })
            }
    }

    fun updateDb(): Observable<out List<CurrencyEntity>> {
        return apiRepository.getCurrency()
            .doOnNext { list ->
                databaseRepository.update(list)
                    .subscribe({
                        Log.d("debug", "обновил в БД")
                    }, {
                        Log.d("debug", "при обновлении в БД ошибка ${it.message}")
                    })
            }
            .filter { it.isNotEmpty() }
            .firstOrError()
            .doOnError { Log.d("debug", "error $it") }
            .map { ToCurrencyEntityMapper.map(it) }
            .toObservable()
    }
}