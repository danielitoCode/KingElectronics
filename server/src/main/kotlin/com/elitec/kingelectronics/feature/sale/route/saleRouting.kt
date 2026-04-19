package com.elitec.kingelectronics.feature.sale.route

import com.elitec.kingelectronics.feature.sale.domain.caseUse.DeleteSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.GetAllSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.GetAllSaleWhitLimitsCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.GetSaleByIdCaseUse
import com.elitec.kingelectronics.feature.sale.domain.caseUse.SaveNewSaleCaseUse
import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
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
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.saleRoutes() {
    val getAll: GetAllSaleCaseUse by inject()
    val getById: GetSaleByIdCaseUse by inject()
    val getAllWithLimit: GetAllSaleWhitLimitsCaseUse by inject()
    val save: SaveNewSaleCaseUse by inject()
    val delete: DeleteSaleCaseUse by inject()

    routing {
        route("/sale"){
            get {
                val sales = getAll()

                call.respond(HttpStatusCode.OK, sales)
            }

            get("/{id}") {
                val id = call.requireId()
                val sale = getById(id) ?: throw NotFoundException("Sale with id $id not found")

                call.respond(HttpStatusCode.OK, sale)
            }

            get("/limit/{limit}") {
                val limit = call.inspectLimit() ?: 20
                val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0
                val sales = getAllWithLimit(limit,offset)

                call.respond(HttpStatusCode.OK, sales)
            }

            delete("/{id}") {
                val id = call.requireId()
                delete(id)

                call.respond(HttpStatusCode.OK)
            }

            post {
                val newSale = call.receive<Sale>()
                val createdId = save(newSale)

                call.respond(HttpStatusCode.Created, createdId)
            }
        }
    }
}