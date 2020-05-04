package ru.kpfu.itis.quailly.egg

import ru.kpfu.itis.quailly.egg.domain.account.AccountCreationData
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.domain.model.SwipeDirection
import java.time.OffsetDateTime

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

fun merchandise(name: String, authorId: Long) = Merchandise(
    name = name,
    description = "the best merchandise ever",
    categoryId = 1,
    authorId = authorId,
    desiredCategoryIds = listOf(1, 2, 3)
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

fun accountCreationData(name: String) = AccountCreationData(
    email = name,
    emailVerified = true,
    name = name,
    pictureUrl = "pict",
    locale = "en",
    familyName = name,
    givenName = name
)
