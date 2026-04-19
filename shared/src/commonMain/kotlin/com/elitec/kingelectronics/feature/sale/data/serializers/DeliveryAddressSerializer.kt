package com.elitec.kingelectronics.feature.sale.data.serializers

import com.elitec.kingelectronics.feature.sale.domain.entity.DeliveryAddress
import com.elitec.kingelectronics.feature.sale.domain.entity.SaleItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object DeliveryAddressSerializer {
    private val json by lazy { Json }

    fun serialize(deliveryAddress: DeliveryAddress?): String? {
        if (deliveryAddress == null) return null
        return json.encodeToString(deliveryAddress)
    }

    fun deserialize(codeText: String?): DeliveryAddress? {
        if (codeText.isNullOrEmpty()) return null
        return json.decodeFromString(codeText)
    }
}