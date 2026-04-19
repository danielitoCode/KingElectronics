package com.elitec.kingelectronics.feature.sale.domain.entity

enum class DeliveryType {
    PICKUP,   // El cliente va a recogerlo al taller
    DELIVERY  // El cliente solicita domicilio (el taller coordina)
}

fun String.toDeliveryType(): DeliveryType {
    return when(this) {
        "PICKUP", "pickup" -> DeliveryType.PICKUP
        else -> DeliveryType.DELIVERY
    }
}
