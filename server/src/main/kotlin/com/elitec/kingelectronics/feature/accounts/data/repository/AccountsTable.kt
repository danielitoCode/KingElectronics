package com.elitec.kingelectronics.feature.accounts.data.repository

import org.jetbrains.exposed.v1.core.Table

object AccountsTable: Table("accounts_table") {
    val id = long("id").autoIncrement()
    val name = varchar("name", length = 50)
    val email = varchar("email", length = 255)
    val passwordHash = varchar("password", length = 16)
    val photoUrl = varchar("photo_url", 255)
}