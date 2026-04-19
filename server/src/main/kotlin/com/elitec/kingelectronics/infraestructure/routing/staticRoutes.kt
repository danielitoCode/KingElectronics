package com.elitec.kingelectronics.infraestructure.routing

import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.defaultRouting(): RoutingRoot =
    routing {
        get("/") {
            call.respondText("Esto es una prueba de backend con Kotlin")
        }
    }