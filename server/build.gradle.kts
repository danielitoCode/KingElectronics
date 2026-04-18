plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.elitec.kingelectronics"
version = "1.0.0"
application {
    mainClass.set("com.elitec.kingelectronics.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)

    // Content negociation
    implementation(libs.ktor.serializationJson)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.sessions)
    // Persistence
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.h2)
    // DI
    //implementation(platform("io.insert-koin:koin-bom: 4.1.1"))
    // Koin for Ktor
    implementation("io.insert-koin:koin-ktor:4.1.2-Beta1")
    // SLF4J Logger
    implementation("io.insert-koin:koin-logger-slf4j:4.1.2-Beta1")
}