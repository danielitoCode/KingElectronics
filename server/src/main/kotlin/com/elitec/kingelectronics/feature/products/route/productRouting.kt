package com.elitec.kingelectronics.feature.products.route

import com.elitec.kingelectronics.feature.categories.domain.caseUse.ModifyCategoryCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.DeleteProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetAllProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetProductByIdCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.ModifyExistingProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.SaveNewProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.entity.Product
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.productsRoutes(): RoutingRoot =
    routing {
        get("/product") {
            val getAllProductsCaseUse =  call.inject<GetAllProductCaseUse>().value
            getAllProductsCaseUse()
                .onSuccess {
                    call.respond(HttpStatusCode.OK, it)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        get("/product/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val getProductByIdCaseUse =  call.inject<GetProductByIdCaseUse>().value
            getProductByIdCaseUse(id)
                .onSuccess { result ->
                    if (result == null) call.respond(HttpStatusCode.NotFound)
                    call.respond(HttpStatusCode.OK, result!!)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        delete("/product/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val deleteProductCaseUse =  call.inject<DeleteProductCaseUse>().value
            deleteProductCaseUse(id)
                .onSuccess {
                    call.respond(HttpStatusCode.OK)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        post("/product") {
            val newProduct = call.receive<Product>()
            val saveProductCaseUse =  call.inject<SaveNewProductCaseUse>().value
            saveProductCaseUse(newProduct)
                .onSuccess { createdId ->
                    call.respond(HttpStatusCode.OK, createdId)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        put("/product/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val modifiedProduct = call.receive<Product>()
            val modifyProductCaseUse =  call.inject<ModifyExistingProductCaseUse>().value
            modifyProductCaseUse(id, modifiedProduct)
                .onSuccess {
                    call.respond(HttpStatusCode.OK)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }
    }
