package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.repository.AccountRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.ACCOUNT

@Repository
open class JooqAccountRepository(private val jooq: DSLContext) : AccountRepository {

    override fun create(entity: Account): Account =
        jooq.insertInto(ACCOUNT)
            .columns(
                ACCOUNT.USERNAME,
                ACCOUNT.EMAIL,
                ACCOUNT.EMAILVERIFIED,
                ACCOUNT.FIRSTNAME,
                ACCOUNT.FAMILYNAME,
                ACCOUNT.GIVENNAME,
                ACCOUNT.PICTUREURL,
                ACCOUNT.LOCALE,
                ACCOUNT.PASSWORD,
                ACCOUNT.PHONENUMBER,
                ACCOUNT.LASTVISIT,
                ACCOUNT.REGISTRATIONDATETIME,
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
                entity.lastVisit?.toOffsetDateTime(),
                entity.registrationDateTime.toOffsetDateTime(),
                entity.token
            )
            .returning()
            .fetchOne()
            .into(Account::class.java)


    override fun getById(id: Long): Account {
        TODO("Not yet implemented")
    }

    override fun getAll(): Iterable<Account> {
        TODO("Not yet implemented")
    }

    override fun update(entity: Account): Account {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Account) {
        TODO("Not yet implemented")
    }

    override fun findByEmail(email: String): Account? {
        TODO("Not yet implemented")
    }

    override fun findByToken(token: String): Account? =
        jooq.select().from(ACCOUNT)
            .where(ACCOUNT.TOKEN.eq(token))
            .fetchOne()
            .into(Account::class.java)

}