package com.elitec.kingelectronics.feature.promotions.di

import com.elitec.kingelectronics.feature.promotions.data.repository.PromotionDataSource
import com.elitec.kingelectronics.feature.promotions.data.repository.PromotionRepositoryImpl
import com.elitec.kingelectronics.feature.promotions.domain.caseUse.DeletePromotionCaseUse
import com.elitec.kingelectronics.feature.promotions.domain.caseUse.GetAllPromotionsCaseUse
import com.elitec.kingelectronics.feature.promotions.domain.caseUse.GetPromotionsByIdCaseUse
import com.elitec.kingelectronics.feature.promotions.domain.caseUse.ModifyExistingPromotionCaseUse
import com.elitec.kingelectronics.feature.promotions.domain.caseUse.SaveNewPromotionCaseUse
import com.elitec.kingelectronics.feature.promotions.domain.repository.PromotionRepository
import org.koin.dsl.module

val promotionDiModule = module {
    single { PromotionDataSource(get()) }
    single<PromotionRepository> { PromotionRepositoryImpl(get()) }

    factory { DeletePromotionCaseUse(get()) }
    factory { GetAllPromotionsCaseUse(get()) }
    factory { GetPromotionsByIdCaseUse(get()) }
    factory { ModifyExistingPromotionCaseUse(get()) }
    factory { SaveNewPromotionCaseUse(get()) }
}