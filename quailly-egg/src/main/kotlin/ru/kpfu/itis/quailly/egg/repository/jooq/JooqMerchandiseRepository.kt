package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.DESIRED_MERCHANDISE_CATALOG
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.SWIPE
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.tables.Merchandise.MERCHANDISE

@Repository
open class JooqMerchandiseRepository(private val jooq: DSLContext) : MerchandiseRepository {

    override fun create(entity: Merchandise): Merchandise =
        jooq.transactionResult { configuration ->
            val context = DSL.using(configuration)

            val createdMerchandise = context.insertInto(MERCHANDISE)
                .columns(
                    MERCHANDISE.NAME,
                    MERCHANDISE.DESCRIPTION,
                    MERCHANDISE.CATEGORY_ID,
                    MERCHANDISE.AUTHOR_ID,
                    MERCHANDISE.CREATED,
                    MERCHANDISE.PICTURE_URL
                )
                .values(
                    entity.name,
                    entity.description,
                    entity.categoryId,
                    entity.authorId,
                    entity.created,
                    entity.pictureUrl
                )
                .returning()
                .fetchOne()
                .map {
                    Merchandise(
                        id = it.get(MERCHANDISE.ID),
                        name = it.get(MERCHANDISE.NAME),
                        description = it.get(MERCHANDISE.DESCRIPTION),
                        categoryId = it.get(MERCHANDISE.CATEGORY_ID),
                        authorId = it.get(MERCHANDISE.AUTHOR_ID),
                        desiredCategoryIds = entity.desiredCategoryIds,
                        pictureUrl = it.get(MERCHANDISE.PICTURE_URL),
                        created = it.get(MERCHANDISE.CREATED)
                    )
                }

            entity.desiredCategoryIds.forEach {
                context.insertInto(DESIRED_MERCHANDISE_CATALOG)
                    .columns(DESIRED_MERCHANDISE_CATALOG.MERCHANDISE_ID, DESIRED_MERCHANDISE_CATALOG.CATEGORY_ID)
                    .values(createdMerchandise.id, it)
                    .execute()
            }
            createdMerchandise
        }

    override fun getAllForAuthor(authorId: Long): List<Merchandise> {
        return jooq.select().from(MERCHANDISE)
            .where(MERCHANDISE.AUTHOR_ID.eq(authorId))
            .fetch()
            .into(Merchandise::class.java)
    }

    override fun getNextMerchandisesForReview(limit: Long, authorId: Long): List<Merchandise> {
        return jooq.select(*MERCHANDISE.fields(), DESIRED_MERCHANDISE_CATALOG.CATEGORY_ID.`as`("desired_catalog"))
            .from(MERCHANDISE)
            .join(DESIRED_MERCHANDISE_CATALOG).on(DESIRED_MERCHANDISE_CATALOG.MERCHANDISE_ID.eq(MERCHANDISE.ID))
            .where(MERCHANDISE.AUTHOR_ID.notEqual(authorId))
            .and(
                MERCHANDISE.CATEGORY_ID.`in`(
                    jooq.selectDistinct(DESIRED_MERCHANDISE_CATALOG.CATEGORY_ID).from(MERCHANDISE)
                        .join(DESIRED_MERCHANDISE_CATALOG)
                        .on(MERCHANDISE.CATEGORY_ID.eq(DESIRED_MERCHANDISE_CATALOG.CATEGORY_ID))
                        .where(MERCHANDISE.AUTHOR_ID.equal(authorId))
                )
            )
            .except(
                jooq.select(*MERCHANDISE.fields(), DESIRED_MERCHANDISE_CATALOG.CATEGORY_ID.`as`("desired_catalog"))
                    .from(MERCHANDISE)
                    .join(SWIPE).on(SWIPE.MERCHANDISE_ID.eq(MERCHANDISE.ID))
                    .join(DESIRED_MERCHANDISE_CATALOG).on(DESIRED_MERCHANDISE_CATALOG.MERCHANDISE_ID.eq(MERCHANDISE.ID))
                    .where(MERCHANDISE.AUTHOR_ID.eq(authorId))
            )
            .limit(limit)
            .fetch()
            .intoGroups(MERCHANDISE)
            .map {
                Merchandise(
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
    }
}