package com.elitec.kingelectronics.feature.categories.data.repository

import org.jetbrains.exposed.v1.core.Table

object CategoryTable: Table("categories") {
    val id = long("id").autoIncrement()
    val name = varchar("name", length = 50)
    val iconUrl = varchar("icon_url", length = 255)

    override val primaryKey = PrimaryKey(id)
}