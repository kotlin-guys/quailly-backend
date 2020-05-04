package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.ACCOUNT

@Repository
open class JooqAccountRepository(private val jooq: DSLContext) : AccountRepository {

    override fun create(entity: Account): Account =
        jooq.insertInto(ACCOUNT)
            .columns(
                ACCOUNT.USERNAME,
                ACCOUNT.EMAIL,
                ACCOUNT.EMAIL_VERIFIED,
                ACCOUNT.FIRST_NAME,
                ACCOUNT.FAMILY_NAME,
                ACCOUNT.GIVEN_NAME,
                ACCOUNT.PICTURE_URL,
                ACCOUNT.LOCALE,
                ACCOUNT.PASSWORD,
                ACCOUNT.PHONE_NUMBER,
                ACCOUNT.LAST_VISIT,
                ACCOUNT.REGISTRATION_DATETIME,
                ACCOUNT.TOKEN
            )
            .values(
                entity.username,
                entity.email,
                entity.emailVerified,
                entity.firstName,
                entity.familyName,
                entity.givenName,
                entity.pictureUrl,
                entity.locale,
                entity.password,
                entity.phoneNumber,
                entity.lastVisit,
                entity.registrationDatetime,
                entity.token
            )
            .returning()
            .fetchOne()
            .into(Account::class.java)


    override fun findByEmail(email: String): Account? =
        jooq.select().from(ACCOUNT)
            .where(ACCOUNT.EMAIL.eq(email))
            .fetchOne()
            ?.let { it.into(Account::class.java) }

    override fun findByToken(token: String): Account? =
        jooq.select().from(ACCOUNT)
            .where(ACCOUNT.TOKEN.eq(token))
            .fetchOne()
            ?.let { it.into(Account::class.java) }

}