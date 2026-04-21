package com.elitec.kingelectronics.infraestructure.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.elitec.kingelectronics.core.ConfigManager
import io.ktor.server.application.ApplicationEnvironment

object JwtConfig {

    fun verifier(secret: String, issuer: String): JWTVerifier {

        return JWT.require(Algorithm.HMAC256(secret))
            .withIssuer(issuer)
            .build()
    }
}