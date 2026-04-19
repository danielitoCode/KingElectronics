package com.elitec.kingelectronics.feature.sale.data.mapper

import com.elitec.kingelectronics.feature.sale.data.dto.SaleDto
import com.elitec.kingelectronics.feature.sale.data.serializers.DeliveryAddressSerializer
import com.elitec.kingelectronics.feature.sale.data.serializers.SaleItemSerializer
import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import com.elitec.kingelectronics.feature.sale.domain.entity.SaleItem
import com.elitec.kingelectronics.feature.sale.domain.entity.toBuyState
import com.elitec.kingelectronics.feature.sale.domain.entity.toDeliveryType

fun Sale.toData(): SaleDto =
    SaleDto(
        id = this.id,
        date = this.date,
        amount = this.amount,
        verified = this.verified.name,
        products = SaleItemSerializer.serialize(this.products),
        userId = this.userId,
        customerName = this.customerName,
        deliveryAddress = DeliveryAddressSerializer.serialize(this.deliveryAddress),
        deliveryType = this.deliveryType?.name
    )

fun SaleDto.toDomain(): Sale =
    Sale(
        id = this.id,
        date = this.date,
        amount = this.amount,
        verified = this.verified.toBuyState(),
        products = SaleItemSerializer.deserialize(this.products),
        userId = this.userId,
        customerName = this.customerName,
        deliveryType = this.deliveryType?.toDeliveryType(),
        deliveryAddress = DeliveryAddressSerializer.deserialize(this.deliveryAddress)
    )

