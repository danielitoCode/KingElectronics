package com.elitec.kingelectronics.core

import com.elitec.kingelectronics.feature.categories.di.categoryDiModule
import com.elitec.kingelectronics.feature.products.di.productDiModule
import com.elitec.kingelectronics.feature.sale.di.saleDiModule
import com.elitec.kingelectronics.infraestructure.di.infrastructureModule
import com.elitec.kingelectronics.infraestructure.security.JwtConfig
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
//import io.ktor.client.engine.cio.CIO
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.OAuthServerSettings
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.auth.oauth
import io.ktor.server.plugins.NotFoundException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    val env = this.environment

    val dotenv = dotenv {
        ignoreIfMissing = true
    }

    install(Koin) {
        slf4jLogger()
        modules(
            infrastructureModule,
            categoryDiModule,
            productDiModule,
            saleDiModule
        )
    }
    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.message ?: "Invalid request")
        }

        exception<NotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, cause.message ?: "Not found")
        }

        exception<Throwable> { call, cause ->
            call.application.log.error("Unhandled error", cause)
            call.respond(HttpStatusCode.InternalServerError, "Internal server error")
        }
    }
    install(Authentication) {

        jwt("auth-jwt") {
            verifier(
                JwtConfig.verifier(
                    dotenv["SECRET"],
                    dotenv["ISSUER"]
                )
            )

            validate { credential ->
                val userId = credential.payload.getClaim("userId").asLong()
                if (userId != null) JWTPrincipal(credential.payload)
                else null
            }
        }

        oauth("google-oauth") {

            urlProvider = { "http://localhost:8080/callback" }

            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://oauth2.googleapis.com/token",
                    requestMethod = HttpMethod.Post,
                    clientId = dotenv["GOOGLE_URL"],
                    clientSecret = dotenv["GOOGLE_URL"],
                    defaultScopes = listOf("email", "profile")
                )
            }

            client = HttpClient(CIO)
        }
    }
}
