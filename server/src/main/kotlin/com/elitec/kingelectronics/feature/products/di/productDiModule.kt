package com.elitec.kingelectronics.feature.products.di

import com.elitec.kingelectronics.feature.products.data.repository.ProductRepositoryImpl
import com.elitec.kingelectronics.feature.products.data.repository.ProductDataSource
import com.elitec.kingelectronics.feature.products.domain.caseUse.DeleteProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetAllProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetAllProductWithLimitsCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetProductByIdCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.ModifyExistingProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.SaveNewProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository
import org.koin.dsl.module

val productDiModule = module {
    single { ProductDataSource(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }

    factory { GetProductByIdCaseUse(get()) }
    factory { GetAllProductCaseUse(get()) }
    factory { DeleteProductCaseUse(get()) }
    factory { ModifyExistingProductCaseUse(get()) }
    factory { SaveNewProductCaseUse(get()) }
    factory { GetAllProductWithLimitsCaseUse(get()) }
}