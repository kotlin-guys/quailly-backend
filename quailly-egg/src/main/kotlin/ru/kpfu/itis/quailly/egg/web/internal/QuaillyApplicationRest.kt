package ru.kpfu.itis.quailly.egg.web.internal

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/internal")
@RestController
class QuaillyApplicationRest {

    @GetMapping
    fun quailly() = "quailly"
}
