package com.elitec.kingelectronics.feature.products.route

import com.elitec.kingelectronics.feature.products.domain.caseUse.DeleteProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetAllProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetAllProductWithLimitsCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.GetProductByIdCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.ModifyExistingProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.caseUse.SaveNewProductCaseUse
import com.elitec.kingelectronics.feature.products.domain.entity.Product
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

fun Application.productsRoutes() {
    val getAll: GetAllProductCaseUse by inject()
    val getAllWithLimit: GetAllProductWithLimitsCaseUse by inject()
    val getById: GetProductByIdCaseUse by inject()
    val save: SaveNewProductCaseUse by inject()
    val modify: ModifyExistingProductCaseUse by inject()
    val delete: DeleteProductCaseUse by inject()

    routing {
        route("/products") {
            get {
                val products = getAll()

                call.respond(HttpStatusCode.OK, products)
            }

            get("/{id}") {
                val id = call.requireId()
                val product = getById(id) ?: throw NotFoundException("Product with id $id not found")

                call.respond(HttpStatusCode.OK, product)
            }

            get("/limit/{limit}") {
                val limit = call.inspectLimit() ?: 20
                val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0
                val products = getAllWithLimit(limit,offset)

                call.respond(HttpStatusCode.OK, products)
            }

            delete("/{id}") {
                val id = call.requireId()
                delete(id)

                call.respond(HttpStatusCode.OK)
            }

            post {
                val newProduct = call.receive<Product>()
                val createdId = save(newProduct)

                call.respond(HttpStatusCode.Created, createdId)
            }

            put("/{id}") {
                val id = call.requireId()
                val modifiedProduct = call.receive<Product>()
                modify(id, modifiedProduct)

                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
