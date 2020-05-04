package ru.kpfu.itis.quailly.egg.web

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class SwipeIT {

}