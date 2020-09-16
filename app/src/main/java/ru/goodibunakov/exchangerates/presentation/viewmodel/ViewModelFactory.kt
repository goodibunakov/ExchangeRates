package ru.goodibunakov.exchangerates.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.goodibunakov.exchangerates.domain.LocalRepository
import ru.goodibunakov.exchangerates.domain.usecase.GetDataUseCase

class ViewModelFactory(
    private val getDataUseCase: GetDataUseCase,
    private val localRepository: LocalRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(getDataUseCase, localRepository) as T

        return super.create(modelClass)
    }
}