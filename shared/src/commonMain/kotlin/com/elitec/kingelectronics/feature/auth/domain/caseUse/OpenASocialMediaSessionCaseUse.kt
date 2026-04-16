package com.elitec.kingelectronics.feature.auth.domain.caseUse

import com.elitec.kingelectronics.feature.auth.domain.repository.SessionManager
import com.elitec.kingelectronics.infraestructure.domain.enums.SocialMedia

class OpenASocialMediaSessionCaseUse(
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(
        socialMedia: SocialMedia
    ): Result<String> = runCatching {
        sessionManager.openSocialMediaSession(socialMedia)
        "Token" // Pensar en mecanismos de autorizacion y sessiones
    }
}