package ru.kpfu.itis.quailly.egg.domain.merchandise

import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository

@Service
class MerchandiseSwipingService(
    private val merchandiseRepository: MerchandiseRepository
) {

    fun getNextMerchandisesForReview(limit: Long, accountId: Long): List<Merchandise> {
        return merchandiseRepository.getNextMerchandisesForReview(limit, accountId)
    }

}