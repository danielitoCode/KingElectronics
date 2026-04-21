package com.elitec.kingelectronics.feature.auth.domain.entity

data class RefreshToken(
    val userId: Long,
    val token: String,
    val expiresAt: Long
)