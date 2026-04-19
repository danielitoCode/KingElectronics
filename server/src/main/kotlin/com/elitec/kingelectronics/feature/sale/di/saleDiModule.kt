package com.elitec.kingelectronics.feature.sale.di

import com.elitec.kingelectronics.feature.sale.data.repository.SaleRepositoryImpl
import com.elitec.kingelectronics.feature.sale.data.repository.SaleService
import com.elitec.kingelectronics.feature.sale.domain.caseUse.DeleteSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.GetAllSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.GetSaleByIdCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.SaveNewSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository
import org.koin.dsl.module

val saleDiModule = module {
    single { SaleService(get()) }
    single<SaleRepository> { SaleRepositoryImpl(get()) }

    factory { GetSaleByIdCaseUse(get()) }
    factory { GetAllSaleCaseUse(get()) }
    factory { SaveNewSaleCaseUse(get()) }
    factory { DeleteSaleCaseUse(get()) }
}