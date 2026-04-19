package com.elitec.kingelectronics.core

import com.elitec.kingelectronics.feature.categories.route.categoryRoutes
import com.elitec.kingelectronics.feature.products.route.productsRoutes
import com.elitec.kingelectronics.feature.sale.route.saleRoutes
import com.elitec.kingelectronics.infraestructure.routing.defaultRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    defaultRouting()
    categoryRoutes()
    productsRoutes()
    saleRoutes()
}