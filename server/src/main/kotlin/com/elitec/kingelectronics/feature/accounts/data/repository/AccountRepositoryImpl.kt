package com.elitec.kingelectronics.feature.accounts.data.repository

import com.elitec.kingelectronics.feature.account.data.mapper.toDomain
import com.elitec.kingelectronics.feature.account.data.mapper.toDto
import com.elitec.kingelectronics.feature.account.domain.entity.User
import com.elitec.kingelectronics.feature.account.domain.repository.AccountsRepository

class AccountRepositoryImpl(
    private val dataSource: AccountDataSource
): AccountsRepository {
    override suspend fun save(newAccount: User): Long = dataSource.create(newAccount.toDto())

    override suspend fun modify(accountId: Long, modifiedAccount: User, ) =
        dataSource.update(accountId, modifiedAccount.toDto())

    override suspend fun getAccountById(accountId: Long): User? = dataSource.read(accountId)?.toDomain()

    override suspend fun getAllUsers(): List<User> = dataSource.readAll().map { it.toDomain() }

    override suspend fun deleteAccount(accountId: Long) = dataSource.delete(accountId)
}