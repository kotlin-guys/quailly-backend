package ru.kpfu.itis.quailly.egg.domain.model


data class Merchandise(
    val id: Long? = null,
    val name: String,
    val description: String,
    val category: MerchantCategory,
    val author: Account
)
