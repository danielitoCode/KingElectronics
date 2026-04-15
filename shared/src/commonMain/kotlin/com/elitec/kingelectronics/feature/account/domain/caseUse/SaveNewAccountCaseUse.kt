package com.elitec.kingelectronics.feature.account.domain.caseUse

import com.elitec.kingelectronics.feature.account.domain.entity.User
import com.elitec.kingelectronics.feature.account.domain.repository.AccountsRepository

class SaveNewAccountCaseUse(
    private val repository: AccountsRepository
) {
    suspend operator fun invoke( newAccount: User): Result<Unit> = runCatching {
        repository.save(newAccount)
    }
}