package ru.kpfu.itis.quailly.egg.domain.model

import java.time.OffsetDateTime


data class Merchandise(
    val id: Long? = null,
    val name: String,
    val description: String,
    val pictureUrl: String,
    val created: OffsetDateTime,
    val categoryId: Long,
    val authorId: Long,
    val desiredCategoryIds: List<Long>
)
