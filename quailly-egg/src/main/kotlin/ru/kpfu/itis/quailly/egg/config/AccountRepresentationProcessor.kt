package ru.kpfu.itis.quailly.egg.config

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelProcessor
import org.springframework.stereotype.Component
import ru.kpfu.itis.quailly.egg.domain.model.Account

@Component
class AccountRepresentationProcessor() : RepresentationModelProcessor<EntityModel<Account>> {

    override fun process(model: EntityModel<Account>): EntityModel<Account> {
        val content = model.content ?: error("Null not allowed")
        if (content.merchandises.isEmpty()) {
            model.removeLinks()
        }
        return model
    }
}