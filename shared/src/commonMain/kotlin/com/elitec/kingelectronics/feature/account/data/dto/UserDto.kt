package com.elitec.kingelectronics.feature.account.data.dto

data class UserDto (
    val id: Long,
    val name: String,
    val email: String,
    val pass: String,
    val sub: String,
    val phone: String? = "",
    val photoUrl: String? = "",
    val verification: Boolean = false
)