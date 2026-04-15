package com.elitec.kingelectronics.infraestructure.domain.enums

private val OPERATOR_ALLOWED_ROLES = setOf("operator", "admin", "administrator", "owner")

fun String?.normalizeBusinessRole(): String? = this?.trim()?.lowercase()?.takeIf { it.isNotEmpty() }

fun String?.hasOperatorAccess(): Boolean = normalizeBusinessRole() in OPERATOR_ALLOWED_ROLES
