package ru.kpfu.itis.quailly.egg.domain.merchandise

data class MerchandiseCreationRequest(
    val name: String,
    val description: String,
    val category: String,
    val desiredCategories: Set<String>
)