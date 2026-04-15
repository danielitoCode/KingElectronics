package com.elitec.kingelectronics.feature.account.domain.entity

data class User (
    val id: String,
    val name: String,
    val email: String,
    val pass: String,
    val sub: String,
    val phone: String? = "",
    val photoUrl: String? = "",
    val verification: Boolean = false
)
