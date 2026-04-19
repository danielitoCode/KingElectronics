package com.elitec.kingelectronics.feature.sale.data.dto

import com.elitec.kingelectronics.feature.sale.domain.entity.BuyState
import com.elitec.kingelectronics.feature.sale.domain.entity.DeliveryAddress
import com.elitec.kingelectronics.feature.sale.domain.entity.DeliveryType
import com.elitec.kingelectronics.feature.sale.domain.entity.SaleItem

data class SaleDto(
    val id: Long,
    //val date: LocalDate,
    val date: String,
    val amount: Double,
    val verified: String,
    val products: String,
    val userId: Long,
    val customerName: String? = null,
    val deliveryType: String? = null,
    val deliveryAddress: String? = null
)