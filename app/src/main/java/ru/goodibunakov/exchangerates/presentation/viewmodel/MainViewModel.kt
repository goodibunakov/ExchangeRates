package ru.goodibunakov.exchangerates.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.goodibunakov.exchangerates.domain.LocalRepository
import ru.goodibunakov.exchangerates.domain.usecase.GetDataUseCase
import ru.goodibunakov.exchangerates.presentation.ToCurrencyUiMapper
import ru.goodibunakov.exchangerates.presentation.model.CurrencySaved
import ru.goodibunakov.exchangerates.presentation.model.CurrencyUi
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val getDataUseCase: GetDataUseCase,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val dataLiveData = MutableLiveData<List<CurrencyUi>>()
    val errorLiveData = MutableLiveData(false)

    val savedCurrencyLiveData = MutableLiveData<CurrencySaved?>()
    val resultLiveData = MutableLiveData<String>()
    val showLoadingLiveData = MutableLiveData(false)

    init {
        getData()
        getSavedCurrency()
    }

    private fun getSavedCurrency() {
        localRepository.load()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                savedCurrencyLiveData.value = it
            }, {
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }

    private fun getData() {
        getDataUseCase.data()
            .map { ToCurrencyUiMapper.map(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoadingLiveData.value = true }
            .doOnError { showLoadingLiveData.value = false }
            .doOnNext { showLoadingLiveData.value = false }
            .subscribe({
                dataLiveData.value = it
                errorLiveData.value = false
            }, {
                errorLiveData.value = true
            })
            .addTo(compositeDisposable)
    }

    fun saveCurrency(item: CurrencyUi) {
        localRepository.save(item).subscribe().addTo(compositeDisposable)
    }

    fun update() {
        getDataUseCase.updateDb()
            .repeatWhen { objectObservable -> objectObservable.delay(30, TimeUnit.SECONDS) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ToCurrencyUiMapper.map(it) }
            .doOnSubscribe { showLoadingLiveData.value = true }
            .doOnError { showLoadingLiveData.value = false }
            .doOnNext { showLoadingLiveData.value = false }
            .subscribe({
                dataLiveData.value = it
                errorLiveData.value = false
            }, {
                errorLiveData.value = true
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun calculate(enterText: String) {
        Single.fromCallable {
            enterText.toFloat() / savedCurrencyLiveData.value!!.value / savedCurrencyLiveData.value!!.nominal
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                resultLiveData.value = String.format("%.03f", it)
            }, {})
            .addTo(compositeDisposable)
    }
}