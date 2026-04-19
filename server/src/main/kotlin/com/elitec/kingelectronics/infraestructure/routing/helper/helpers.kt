package com.elitec.kingelectronics.infraestructure.routing.helper

import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.log

fun ApplicationCall.requireId(): Long =
    parameters["id"]?.toLongOrNull()
        ?: throw IllegalArgumentException("Invalid ID")

fun ApplicationCall.inspectLimit(): Int =
    parameters["limit"]?.toIntOrNull()
        ?: throw IllegalArgumentException("Invalid limit paging")

fun ApplicationCall.logError(e: Throwable) {
    application.log.error("Request failed", e)
}