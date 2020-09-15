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

class MainViewModel(
    private val getDataUseCase: GetDataUseCase,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val dataLiveData = MutableLiveData<List<CurrencyUi>>()
    val errorLiveData = MutableLiveData(false)

    val savedCurrencyLiveData = MutableLiveData<CurrencySaved?>()
    val resultLiveData = MutableLiveData<String>()

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

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun calculate(enterText: String) {
        Single.fromCallable {
            enterText.toFloat() / savedCurrencyLiveData.value!!.value / savedCurrencyLiveData.value!!.nominal
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                resultLiveData.value = String.format("%.03f", it)
            }, {})
            .addTo(compositeDisposable)
    }
}