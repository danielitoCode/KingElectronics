package com.elitec.kingelectronics.feature.account.domain.caseUse

import com.elitec.kingelectronics.feature.account.domain.entity.User
import com.elitec.kingelectronics.feature.account.domain.repository.AccountsRepository

class ModifyExistingAccountCaseUse(
    private val repository: AccountsRepository
) {
    suspend operator fun invoke(
        accountId: Long,
        modifiedAccount: User
    ) : Result<Unit> = runCatching {
        repository.modify(accountId,modifiedAccount)
    }
}