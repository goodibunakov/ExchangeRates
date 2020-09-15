package ru.goodibunakov.exchangerates.domain

import io.reactivex.Completable

interface DatabaseRepository {
    /**
     * Вставить в БД лист плейлистов youtube-канала
     */
    fun insert(currencyList: List<Currency>): Completable

//    /**
//     * Удалить все плейлисты в БД
//     */
//    fun deletePlaylists(): Completable
//
//    /**
//     * Обновить плейлисты в БД
//     */
//    fun updatePlaylists(playlistsDTO: PlaylistsDTO): Maybe<Int>
//
//    /**
//     * Получение плейлистов из БД
//     */
//    fun getPlaylists(): Maybe<PlaylistsDTO>
//
//    /**
//     * Сохранить notification в БД
//     */
//    fun saveNotification(messageItem: MessageItem): Completable
//
//    /**
//     * Удалить все сообщения
//     */
//    fun deleteAllNotifications(): Completable
//
//    /**
//     * Получение всех сообщений
//     */
//    fun getAllNotifications(): Observable<List<MessageItem>>
//
//    /**
//     * Сохранение выбранного видео в БД
//     */
//    fun saveStar(videoItemModel: VideoItemModel): Completable
//
//    /**
//     * Удаление выбранного видео из БД
//     */
//    fun deleteStar(videoId: String): Completable
//
//    /**
//     * Получение всех выбранных видео
//     */
//    fun getStars(): Single<List<VideoItemModel>>
//
//    /**
//     * Получение всех id выбранных видео
//     */
//    fun getStarIds(playlistId: String): Observable<List<String>>
}