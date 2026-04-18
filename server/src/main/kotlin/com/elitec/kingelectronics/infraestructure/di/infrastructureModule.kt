package com.elitec.kingelectronics.infraestructure.di

import org.jetbrains.exposed.v1.jdbc.Database
import org.koin.dsl.module

val infrastructureModule = module {
    single {
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user = "root",
            driver = "org.h2.Driver",
            password = "",
        )
    }
}