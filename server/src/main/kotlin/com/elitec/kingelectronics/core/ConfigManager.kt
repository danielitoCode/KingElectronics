package com.elitec.kingelectronics.core

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationEnvironment
object ConfigManager {

    fun isDev(env: ApplicationEnvironment): Boolean {
        return (env.config.propertyOrNull("ktor.environment")
            ?.getString() ?: "dev") == "dev"
    }

    fun get(
        env: ApplicationEnvironment,
        key: String,
        envKey: String
    ): String {

        return if (isDev(env)) {
            env.config.propertyOrNull(key)?.getString()
                ?: error("Missing config key: $key in application.conf")
        } else {
            System.getenv(envKey)
                ?: error("Missing env var: $envKey")
        }
    }
}