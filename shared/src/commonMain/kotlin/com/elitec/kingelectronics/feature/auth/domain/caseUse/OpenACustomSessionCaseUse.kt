package com.elitec.kingelectronics.feature.auth.domain.caseUse

import com.elitec.kingelectronics.feature.auth.domain.repository.SessionManager

class OpenACustomSessionCaseUse(
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(
        email: String, pass: String
    ): Result<String> = runCatching {
        sessionManager.openSession()
        "Token" // pensar en mecanismos de auth
    }
}