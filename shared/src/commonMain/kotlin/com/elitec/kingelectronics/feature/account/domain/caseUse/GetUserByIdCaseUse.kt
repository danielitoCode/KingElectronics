package com.elitec.kingelectronics.feature.account.domain.caseUse

import com.elitec.kingelectronics.feature.account.domain.entity.User
import com.elitec.kingelectronics.feature.account.domain.repository.AccountsRepository

class GetUserByIdCaseUse(
    private val repository: AccountsRepository
) {
    suspend operator fun invoke(accountId: Long) : Result<User?> = runCatching {
        repository.getAccountById(accountId)
    }
}