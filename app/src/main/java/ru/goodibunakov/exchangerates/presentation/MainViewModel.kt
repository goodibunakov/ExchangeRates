package ru.goodibunakov.exchangerates.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.goodibunakov.exchangerates.domain.Currency
import ru.goodibunakov.exchangerates.domain.GetDataUseCase

class MainViewModel(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val dataLiveData = MutableLiveData<List<Currency>>()
    val errorLiveData = MutableLiveData(false)

    init {
        getData()
    }

    private fun getData() {
        getDataUseCase.data()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("debug", "list = $it")
                dataLiveData.value = it
                errorLiveData.value = false
            }, {
                Log.d("debug", "error = $it")
                errorLiveData.value = true
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}