package com.elitec.kingelectronics.feature.sale.route

import com.elitec.kingelectronics.feature.sale.domain.caseUse.DeleteSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.GetAllSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.GetSaleByIdCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.SaveNewSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.saleRoutes(): RoutingRoot =
    routing {
        get("/sale") {
            val getAllSalesCaseUse =  call.inject<GetAllSaleCaseUse>().value
            getAllSalesCaseUse()
                .onSuccess {
                    call.respond(HttpStatusCode.OK, it)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        get("/sale/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val getSaleByIdCaseUse =  call.inject<GetSaleByIdCaseUse>().value
            getSaleByIdCaseUse(id)
                .onSuccess { result ->
                    if (result == null) call.respond(HttpStatusCode.NotFound)
                    call.respond(HttpStatusCode.OK, result!!)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        delete("/sale/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val deleteSaleCaseUse =  call.inject<DeleteSaleCaseUse>().value
            deleteSaleCaseUse(id)
                .onSuccess {
                    call.respond(HttpStatusCode.OK)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        post("/sale") {
            val newSale = call.receive<Sale>()
            val saveSaleCaseUse =  call.inject<SaveNewSaleCaseUse>().value
            saveSaleCaseUse(newSale)
                .onSuccess { createdId ->
                    call.respond(HttpStatusCode.OK, createdId)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }
    }