package com.elitec.kingelectronics.feature.sale.domain.entity

enum class BuyState {
    UNVERIFIED, VERIFIED, DELETED
}

fun String.toBuyState(): BuyState {
    return when(this) {
        "UNVERIFIED","unverified" -> BuyState.UNVERIFIED
        "VERIFIED", "verified" -> BuyState.VERIFIED
        "DELETED", "deleted" -> BuyState.DELETED
        else -> throw IllegalStateException("BuyState incorrect format")
    }
}

