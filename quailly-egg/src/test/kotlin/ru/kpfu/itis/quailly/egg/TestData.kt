package ru.kpfu.itis.quailly.egg

import ru.kpfu.itis.quailly.egg.domain.account.AccountCreationData
import ru.kpfu.itis.quailly.egg.domain.merchandise.MerchandiseCreationRequest
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.domain.model.SwipeDirection
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
    desiredCategoryIds = desiredCategoryIds
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
    categoryId: Long = 1
) = MerchandiseCreationRequest(
    name = name, description = "bla bla", categoryId = categoryId, desiredCategoryIds = listOf(9, 20)
)

fun swipeRequest(
    merchandiseId: Long,
    direction: SwipeDirection = SwipeDirection.LEFT
) = SwipeRequest(
    direction = direction,
    merchandiseId = merchandiseId
)

