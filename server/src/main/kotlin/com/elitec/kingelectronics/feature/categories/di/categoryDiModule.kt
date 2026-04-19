package com.elitec.kingelectronics.feature.categories.di

import com.elitec.kingelectronics.feature.categories.data.repository.CategoryRepositoryImpl
import com.elitec.kingelectronics.feature.categories.data.repository.CategoryDataSource
import com.elitec.kingelectronics.feature.categories.domain.caseUse.DeleteCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetAllCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetAllCategoryWithLimitCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetCategoryByIdCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.ModifyCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.SaveNewCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository
import org.koin.dsl.module

val categoryDiModule = module {
    single { CategoryDataSource(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    factory { DeleteCategoryCaseUse(get()) }
    factory { GetAllCategoryCaseUse(get()) }
    factory { GetCategoryByIdCaseUse(get()) }
    factory { ModifyCategoryCaseUse(get()) }
    factory { SaveNewCategoryCaseUse(get()) }
    factory { GetAllCategoryWithLimitCaseUse(get()) }
}