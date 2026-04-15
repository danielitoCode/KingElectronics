package com.elitec.kingelectronics

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform