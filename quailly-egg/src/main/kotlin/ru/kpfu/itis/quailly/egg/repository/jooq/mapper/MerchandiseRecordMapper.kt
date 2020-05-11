package ru.kpfu.itis.quailly.egg.repository.jooq.mapper

import org.jooq.Record
import org.jooq.Result
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.tables.records.MerchandiseRecord

class MerchandiseRecordMapper : JooqRecordMapper<MerchandiseRecord, Merchandise> {

    override fun map(it: Map.Entry<MerchandiseRecord, Result<Record>>) = Merchandise(
        id = it.key.id,
        name = it.key.name,
        description = it.key.description,
        authorId = it.key.authorId,
        categoryId = it.key.categoryId,
        desiredCategoryIds = it.value.map { categoryId ->
            categoryId.get("desired_catalog", Long::class.java)
        },
        pictureUrl = it.key.pictureUrl,
        created = it.key.created
    )
}