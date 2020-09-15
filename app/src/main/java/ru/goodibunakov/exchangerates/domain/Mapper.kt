package ru.goodibunakov.exchangerates.domain

interface Mapper<in From, out To> {
    fun map(from: From): To
}