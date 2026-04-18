package com.elitec.kingelectronics.feature.categories.route.models

import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val id: Long,
    val name: String,
    val iconUrl: String
) {
    companion object {
        fun CategoryModel.fromRouting(): Category =
            Category(
                id = this.id,
                name = this.name,
                iconUrl = this.iconUrl
            )

        fun Category.toRouting(): CategoryModel =
            CategoryModel(
                id = this.id,
                name = this.name,
                iconUrl = this.iconUrl
            )
    }
}