package com.elitec.kingelectronics.core


import com.elitec.kingelectronics.feature.categories.di.categoryDiModule
import com.elitec.kingelectronics.feature.products.di.productDiModule
import com.elitec.kingelectronics.infraestructure.di.infrastructureModule
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(
            infrastructureModule,
            categoryDiModule,
            productDiModule
        )
    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
}
