package com.elitec.kingelectronics.feature.promotions.data.repository

import com.elitec.kingelectronics.feature.categories.data.repository.CategoryTable
import org.jetbrains.exposed.v1.core.Table

object PromotionTable: Table("promotion") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 100)
    val description = varchar("description", 255)
    val oldPrice = double("old_price")
    val newPrice = double("new_price")
    val photoUrl = varchar("photo_url", 255)

    override val primaryKey = PrimaryKey(CategoryTable.id)
}