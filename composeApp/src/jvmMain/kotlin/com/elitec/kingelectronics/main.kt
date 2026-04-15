package com.elitec.kingelectronics

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KingElectronics",
    ) {
        App()
    }
}