package com.elitec.kingelectronics.feature.account.domain.caseUse

import com.elitec.kingelectronics.feature.account.domain.entity.User
import com.elitec.kingelectronics.feature.account.domain.repository.AccountsRepository

class GetAllAccountsCaseUse(
    private val repository: AccountsRepository
) {
    suspend operator fun invoke(): Result<List<User>> = runCatching {
        repository.getAllUsers()
    }
}