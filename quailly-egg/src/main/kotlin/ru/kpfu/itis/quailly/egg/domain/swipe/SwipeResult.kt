package ru.kpfu.itis.quailly.egg.domain.swipe

sealed class SwipeResult {
    object Success : SwipeResult()
    data class Fail(val details: String) : SwipeResult()
}
