package ru.kpfu.itis.quailly.egg.domain.merchandise

import ru.kpfu.itis.quailly.egg.domain.model.MerchantCategory

data class MerchandiseCreationRequest(
    val name: String,
    val description: String,
    val category: MerchantCategory,
    val desiredCategories: Set<String>
)