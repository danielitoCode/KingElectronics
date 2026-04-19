package com.elitec.kingelectronics.feature.sale.data.serializers

import com.elitec.kingelectronics.feature.sale.domain.entity.SaleItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SaleItemSerializer {
    private val json by lazy { Json }

    fun serialize(saleItem: List<SaleItem>): String {
        return json.encodeToString(saleItem)
    }

    fun deserialize(codeText: String): List<SaleItem> {
        return json.decodeFromString(codeText)
    }
}