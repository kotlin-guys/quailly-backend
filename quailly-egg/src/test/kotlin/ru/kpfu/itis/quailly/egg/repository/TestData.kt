package ru.kpfu.itis.quailly.egg.repository

import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.domain.model.SwipeDirection
import java.time.ZonedDateTime

fun account(name: String) = Account(
    email = name,
    emailVerified = true,
    firstName = name,
    familyName = name,
    givenName = name,
    locale = "nigeria",
    pictureUrl = "random-$name.png",
    registrationDateTime = ZonedDateTime.now()
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