package com.elitec.kingelectronics.feature.categories.route

import com.elitec.kingelectronics.feature.categories.domain.caseUse.DeleteCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetAllCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetCategoryByIdCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.ModifyCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.SaveNewCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.route.models.CategoryModel
import com.elitec.kingelectronics.feature.categories.route.models.CategoryModel.Companion.fromRouting
import com.elitec.kingelectronics.feature.categories.route.models.CategoryModel.Companion.toRouting
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

fun Application.categoryRoutes(): RoutingRoot =
    routing {
        get("/category") {
            val getAllCategoryCaseUse =  call.inject<GetAllCategoryCaseUse>().value
            getAllCategoryCaseUse()
                .onSuccess {
                    call.respond(HttpStatusCode.OK, it.map { data-> data.toRouting() })
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        get("/category/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val getCategoryByIdCaseUse =  call.inject<GetCategoryByIdCaseUse>().value
            getCategoryByIdCaseUse(id)
                .onSuccess { result ->
                    if (result == null) call.respond(HttpStatusCode.NotFound)
                    call.respond(HttpStatusCode.OK, result!!.toRouting())
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        delete("/category/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val deleteCategoryCaseUse =  call.inject< DeleteCategoryCaseUse>().value
            deleteCategoryCaseUse(id)
                .onSuccess {
                    call.respond(HttpStatusCode.OK)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        post("/category") {
            val newCategory = call.receive<CategoryModel>().fromRouting()
            val saveCategoryCaseUse =  call.inject<SaveNewCategoryCaseUse>().value
            saveCategoryCaseUse(newCategory)
                .onSuccess { createdId ->
                    call.respond(HttpStatusCode.OK, createdId)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }

        put("/category/{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
            val modifiedCategory = call.receive<CategoryModel>().fromRouting()
            val modifyCategoryCaseUse =  call.inject<ModifyCategoryCaseUse>().value
            modifyCategoryCaseUse(id, modifiedCategory)
                .onSuccess {
                    call.respond(HttpStatusCode.OK)
                }
                .onFailure {
                    call.respond(HttpStatusCode.InternalServerError)
                }
        }
    }