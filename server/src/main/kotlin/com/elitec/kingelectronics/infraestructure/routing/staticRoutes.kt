package com.elitec.kingelectronics.infraestructure.routing

import com.elitec.kingelectronics.feature.products.data.repository.ProductTable.id
import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import io.ktor.server.response.respondText
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.footer
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.head
import kotlinx.html.header
import kotlinx.html.li
import kotlinx.html.nav
import kotlinx.html.p
import kotlinx.html.style
import kotlinx.html.title
import kotlinx.html.ul
import kotlinx.html.unsafe

fun Application.defaultRouting(): RoutingRoot =
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("KingElectronic - Tecnología a tu alcance")
                    style {
                        unsafe {
                            +"""
                            body {
                                margin: 0;
                                font-family: Arial, sans-serif;
                                background-color: #f5f5f5;
                            }
                            header {
                                background-color: #0d47a1;
                                color: white;
                                padding: 20px;
                                text-align: center;
                            }
                            nav {
                                background-color: #1565c0;
                                padding: 10px;
                                text-align: center;
                            }
                            nav a {
                                color: white;
                                margin: 0 15px;
                                text-decoration: none;
                                font-weight: bold;
                            }
                            .hero {
                                padding: 60px;
                                text-align: center;
                                background-color: #e3f2fd;
                            }
                            .hero h1 {
                                font-size: 40px;
                                margin-bottom: 10px;
                            }
                            .section {
                                padding: 40px;
                                text-align: center;
                            }
                            .products {
                                display: flex;
                                justify-content: center;
                                gap: 20px;
                                flex-wrap: wrap;
                            }
                            .card {
                                background: white;
                                padding: 20px;
                                border-radius: 10px;
                                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                                width: 200px;
                            }
                            footer {
                                background-color: #0d47a1;
                                color: white;
                                text-align: center;
                                padding: 15px;
                                margin-top: 40px;
                            }
                            """.trimIndent()
                        }
                    }
                }

                body {

                    // 🔵 Header
                    header {
                        h1 { +"KingElectronic" }
                        p { +"Tu tienda de artículos electrónicos" }
                    }

                    // 🔵 Navegación
                    nav {
                        a("#") { +"Inicio" }
                        a("#productos") { +"Productos" }
                        a("#contacto") { +"Contacto" }
                    }

                    // 🔵 Hero
                    div("hero") {
                        h1 { +"Tecnología que impulsa tu vida" }
                        p { +"Encuentra los mejores dispositivos electrónicos al mejor precio" }
                    }

                    // 🔵 Sección productos
                    div("section") {
                        val id = "productos"

                        h2 { +"Productos destacados" }

                        div("products") {
                            div("card") {
                                h3 { +"Smartphones" }
                                p { +"Últimos modelos del mercado" }
                            }
                            div("card") {
                                h3 { +"Laptops" }
                                p { +"Potencia y rendimiento para todo" }
                            }
                            div("card") {
                                h3 { +"Accesorios" }
                                p { +"Todo lo que necesitas" }
                            }
                        }
                    }

                    // 🔵 Sección contacto
                    div("section") {
                        val id = "contacto"

                        h2 { +"Contáctanos" }
                        p { +"Email: contacto@kingelectronic.com" }
                        p { +"Teléfono: +1 234 567 890" }
                    }

                    // 🔵 Footer
                    footer {
                        p { +"© 2026 KingElectronic - Todos los derechos reservados" }
                    }
                }
            }
        }
    }