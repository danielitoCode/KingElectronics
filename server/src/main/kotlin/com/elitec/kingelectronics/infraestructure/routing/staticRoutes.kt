package com.elitec.kingelectronics.infraestructure.routing

import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import io.ktor.server.response.respondText
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.li
import kotlinx.html.style
import kotlinx.html.ul

fun Application.defaultRouting(): RoutingRoot =
    routing {
        get("/") {
            call.respondHtml {
                body {
                    h1 {
                        style = "color: blue; font-size: 24px;"
                        +"HTML"
                    }
                    div {
                        +"Esto es una prueba de HTML"
                    }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }
    }