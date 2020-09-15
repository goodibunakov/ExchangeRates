package ru.goodibunakov.exchangerates.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.goodibunakov.exchangerates.domain.GetDataUseCase

class ViewModelFactory(
        private val getDataUseCase: GetDataUseCase
//        private val getNetworkStatusUseCase: GetNetworkStatusUseCase,
//        private val getPlaylistVideosUseCase: GetPlaylistVideosUseCase,
//        private val getVideoDetailsUseCase: GetVideoDetailsUseCase,
//        private val getAllVideosListUseCase: GetAllVideosListUseCase,
//        private val getAboutChannelUseCase: GetAboutChannelUseCase,
//        private val getMessagesUseCase: GetMessagesUseCase,
//        private val deleteMessagesUseCase: DeleteMessagesUseCase,
//        private val saveStarToDbUseCase: SaveStarToDbUseCase,
//        private val deleteStarFromDbUseCase: DeleteStarFromDbUseCase,
//        private val getStarsFromDbUseCase: GetStarsFromDbUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(getDataUseCase) as T

        return super.create(modelClass)
    }
}