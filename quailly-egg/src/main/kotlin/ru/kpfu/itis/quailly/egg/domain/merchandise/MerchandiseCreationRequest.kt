package ru.kpfu.itis.quailly.egg.domain.merchandise

data class MerchandiseCreationRequest(
    val name: String,
    val description: String,
    val pictureUrl: String,
    val categoryId: Long,
    val desiredCategoryIds: List<Long>
)