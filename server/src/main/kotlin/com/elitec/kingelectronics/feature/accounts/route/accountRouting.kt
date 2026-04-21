package com.elitec.kingelectronics.feature.accounts.route

import com.elitec.kingelectronics.feature.account.domain.caseUse.DeleteAccountCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.GetAllAccountsCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.GetUserByIdCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.ModifyExistingAccountCaseUse
import com.elitec.kingelectronics.feature.account.domain.caseUse.SaveNewAccountCaseUse
import com.elitec.kingelectronics.feature.account.domain.entity.User
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
import kotlin.getValue

fun Application.accountsRoutes() {
    val getAll: GetAllAccountsCaseUse by inject()
    // val getAllWithLimit: GetAllW by inject()
    val getById: GetUserByIdCaseUse by inject()
    val save: SaveNewAccountCaseUse by inject()
    val modify: ModifyExistingAccountCaseUse by inject()
    val delete: DeleteAccountCaseUse by inject()

    routing {
        route("/account") {
            get {
                val accounts = getAll()
                call.respond(HttpStatusCode.OK, accounts)
            }

            get("/{id}") {
                val id = call.requireId()
                val account = getById(id) ?: throw NotFoundException("Category with id $id not found")

                call.respond(HttpStatusCode.OK, account)
            }

            /* get("/limit/{limit}") {
                val limit = call.inspectLimit()
                val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0
                val categories = getAllWithLimit(limit,offset)

                call.respond(HttpStatusCode.OK, categories)
            } */

            post {
                val body = call.receive<User>()
                val id = save(body)

                call.respond(HttpStatusCode.Created, id)
            }

            put("/{id}") {
                val id = call.requireId()
                val body = call.receive<User>()

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