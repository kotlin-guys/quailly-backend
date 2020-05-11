package ru.kpfu.itis.quailly.egg

import ru.kpfu.itis.quailly.egg.domain.account.AccountCreationData
import ru.kpfu.itis.quailly.egg.domain.merchandise.MerchandiseCreationRequest
import ru.kpfu.itis.quailly.egg.domain.model.*
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeRequest
import java.time.OffsetDateTime
import java.util.*

fun account(name: String) = Account(
    email = name,
    emailVerified = true,
    firstName = name,
    familyName = name,
    givenName = name,
    locale = "nigeria",
    pictureUrl = "random-$name.png",
    registrationDatetime = OffsetDateTime.now(),
    lastVisit = OffsetDateTime.now()
)

fun merchandise(
    name: String,
    authorId: Long,
    categoryId: Long = 1,
    desiredCategoryIds: List<Long> = listOf(1, 2, 3)
) = Merchandise(
    name = name,
    description = "the best merchandise ever",
    categoryId = categoryId,
    authorId = authorId,
    desiredCategoryIds = desiredCategoryIds,
    pictureUrl = "",
    created = OffsetDateTime.now()
)

fun swipe(
    accountId: Long,
    merchandiseId: Long,
    direction: SwipeDirection = SwipeDirection.LEFT
) = Swipe(
    accountId = accountId,
    merchandiseId = merchandiseId,
    direction = direction
)

fun exchange(
    firstMerchandiseId: Long,
    secondMerchandiseId: Long,
    initiatorId: Long
) = Exchange(
    publicationDateTime = OffsetDateTime.now(),
    initiatorId = initiatorId,
    firstMerchandiseId = firstMerchandiseId,
    secondMerchandiseId = secondMerchandiseId,
    exchangeStatus = ExchangeStatus.COMMUNICATION_PENDING,
    firstAccepted = false,
    secondAccepted = false
)

fun accountCreationData(name: String = UUID.randomUUID().toString()) = AccountCreationData(
    email = name,
    emailVerified = true,
    name = name,
    pictureUrl = "pict",
    locale = "en",
    familyName = name,
    givenName = name
)

fun merchandiseCreationRequest(
    name: String,
    categoryId: Long = 1,
    desiredCategoryIds: List<Long> = listOf(9, 20)
) = MerchandiseCreationRequest(
    name = name,
    description = "bla bla",
    categoryId = categoryId,
    desiredCategoryIds = desiredCategoryIds,
    pictureUrl = ""
)

fun swipeRequest(
    merchandiseId: Long,
    direction: SwipeDirection = SwipeDirection.RIGHT
) = SwipeRequest(
    direction = direction,
    merchandiseId = merchandiseId
)

