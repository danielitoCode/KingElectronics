package com.elitec.kingelectronics.feature.auth.domain.caseUse

import com.elitec.kingelectronics.feature.auth.domain.repository.SessionManager

class CloseSessionCaseUse(
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Result<Unit> = runCatching {
        sessionManager.closeSession()
    }
}