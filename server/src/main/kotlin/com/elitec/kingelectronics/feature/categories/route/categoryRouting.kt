package com.elitec.kingelectronics.feature.categories.route

import com.elitec.kingelectronics.feature.categories.domain.caseUse.DeleteCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetAllCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetAllCategoryWithLimitCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.GetCategoryByIdCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.ModifyCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.caseUse.SaveNewCategoryCaseUse
import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.infraestructure.routing.helper.inspectLimit
import com.elitec.kingelectronics.infraestructure.routing.helper.requireId
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.plugins.NotFoundException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.categoryRoutes() {
    val getAll: GetAllCategoryCaseUse by inject()
    val getAllWithLimit: GetAllCategoryWithLimitCaseUse by inject()
    val getById: GetCategoryByIdCaseUse by inject()
    val save: SaveNewCategoryCaseUse by inject()
    val modify: ModifyCategoryCaseUse by inject()
    val delete: DeleteCategoryCaseUse by inject()

    routing {
        route("/category") {
            get {
                val categories = getAll()
                call.respond(HttpStatusCode.OK, categories)
            }

            get("/{id}") {
                val id = call.requireId()
                val category = getById(id) ?: throw NotFoundException("Category with id $id not found")

                call.respond(HttpStatusCode.OK, category)
            }

            get("/limit/{limit}") {
                val limit = call.inspectLimit() ?: 20
                val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0
                val categories = getAllWithLimit(limit,offset)

                call.respond(HttpStatusCode.OK, categories)
            }

            post {
                val body = call.receive<Category>()
                val id = save(body)

                call.respond(HttpStatusCode.Created, id)
            }

            put("/{id}") {
                val id = call.requireId()
                val body = call.receive<Category>()

                modify(id, body)

                call.respond(HttpStatusCode.OK)
            }

            delete("/{id}") {
                val id = call.requireId()

                delete(id)

                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}