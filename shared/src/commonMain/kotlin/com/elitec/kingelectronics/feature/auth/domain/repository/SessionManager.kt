package com.elitec.kingelectronics.feature.auth.domain.repository

import com.elitec.kingelectronics.infraestructure.domain.enums.SocialMedia

interface SessionManager {
    suspend fun openSession()
    suspend fun openSocialMediaSession(socialMedia: SocialMedia)
    suspend fun closeSession()
    suspend fun refreshSession()
}