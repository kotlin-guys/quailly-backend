package ru.kpfu.itis.quailly.egg.domain.model


data class Merchandise(
    val id: Long? = null,
    val name: String,
    val description: String,
    val categoryId: Long,
    val authorId: Long,
    val desiredCategoryIds: List<Long>
)
