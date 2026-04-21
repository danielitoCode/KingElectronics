package com.elitec.kingelectronics.feature.accounts.data.repository

import com.elitec.kingelectronics.feature.account.data.dto.UserDto
import com.elitec.kingelectronics.feature.categories.data.repository.CategoryTable
import io.ktor.server.plugins.NotFoundException
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update

class AccountDataSource(
    private val db: Database
) {
    init {
        transaction(db) {
            SchemaUtils.create(CategoryTable)
        }
    }

    suspend fun create(userDto: UserDto): Long = dbQuery {
        AccountsTable.insert {
            it[passwordHash] = userDto.pass
            it[email] = userDto.email
            it[name] = userDto.name
            it[photoUrl] = userDto.photoUrl ?: ""
        } [AccountsTable.id]
    }

    suspend fun read(id: Long): UserDto? = dbQuery {
        AccountsTable
            .select(AccountsTable.id eq id)
            .map {
                UserDto(
                    id = it[AccountsTable.id],
                    name = it[AccountsTable.name],
                    photoUrl = it[AccountsTable.photoUrl],
                    email = it[AccountsTable.email],
                    pass = it[AccountsTable.passwordHash],
                    sub = ""
                )
            }
            .singleOrNull()
    }

    suspend fun getAll(limit: Int, offset: Long): List<UserDto> = dbQuery {
        AccountsTable
            .selectAll()
            .limit(limit)
            .offset(offset)
            .map {
                UserDto(
                    id = it[AccountsTable.id],
                    name = it[AccountsTable.name],
                    photoUrl = it[AccountsTable.photoUrl],
                    email = it[AccountsTable.email],
                    pass = it[AccountsTable.passwordHash],
                    sub = ""
                )
            }
    }

    suspend fun readAll(): List<UserDto> = dbQuery {
        AccountsTable.selectAll()
            .map {
                UserDto(
                    id = it[AccountsTable.id],
                    name = it[AccountsTable.name],
                    photoUrl = it[AccountsTable.photoUrl],
                    email = it[AccountsTable.email],
                    pass = it[AccountsTable.passwordHash],
                    sub = ""
                )
            }
    }

    suspend fun update(id: Long, user: UserDto) {
        dbQuery {
            val updatedRows = AccountsTable.update(
                { AccountsTable.id eq id }
            ) {
                it[passwordHash] = user.pass
                it[email] = user.email
                it[name] = user.name
                it[photoUrl] = user.photoUrl ?: ""
            }

            if (updatedRows == 0) {
                throw NoSuchElementException("user with id $id not found")
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            val deletedRows = AccountsTable.deleteWhere { AccountsTable.id eq id }

            if (deletedRows == 0) {
                throw NotFoundException("Account with id $id not found")
            }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}