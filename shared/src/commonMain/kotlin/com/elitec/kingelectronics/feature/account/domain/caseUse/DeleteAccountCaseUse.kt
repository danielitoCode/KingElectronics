package com.elitec.kingelectronics.feature.account.domain.caseUse

import com.elitec.kingelectronics.feature.account.domain.repository.AccountsRepository

class DeleteAccountCaseUse(
    private val repository: AccountsRepository
) {
    suspend operator fun invoke(accountId: Long): Result<Unit> = runCatching {
        repository.deleteAccount(accountId)
    }
}