package com.elitec.kingelectronics.feature.accounts.di

import com.elitec.kingelectronics.feature.account.domain.caseUse.DeleteAccountCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.GetAllAccountsCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.GetUserByIdCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.ModifyExistingAccountCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.SaveNewAccountCaseUse
import com.elitec.kingelectronics.feature.account.domain.repository.AccountsRepository
import com.elitec.kingelectronics.feature.accounts.data.repository.AccountDataSource
import com.elitec.kingelectronics.feature.accounts.data.repository.AccountRepositoryImpl
import org.koin.dsl.module

val accountDiModule = module {
    single { AccountDataSource(get()) }
    single<AccountsRepository> { AccountRepositoryImpl(get()) }

    factory { GetAllAccountsCaseUse(get()) }
    factory { DeleteAccountCaseUse(get()) }
    factory { GetUserByIdCaseUse(get()) }
    factory { ModifyExistingAccountCaseUse(get()) }
    factory { SaveNewAccountCaseUse(get()) }
}