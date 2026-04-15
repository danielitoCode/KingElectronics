package com.elitec.kingelectronics.feature.account.domain.repository

import com.elitec.kingelectronics.feature.account.domain.entity.User

interface AccountsRepository {
    suspend fun save(newAccount: User)
    suspend fun modify(accountId: Long, modifiedAccount: User)
    suspend fun getAccountById(accountId: Long): User?
    suspend fun getAllCategories(): List<User>
    suspend fun deleteAccount(accountId: Long)
}