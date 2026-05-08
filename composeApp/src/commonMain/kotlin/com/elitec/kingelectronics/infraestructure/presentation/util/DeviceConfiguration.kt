package com.elitec.kingelectronics.infraestructure.presentation.util

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

enum class DeviceConfiguration {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;

    companion object {

        fun WindowSizeClass.toDeviceConfiguration(): DeviceConfiguration {
            val isCompactWidth = !isWidthAtLeastBreakpoint(600)
            val isMediumWidth = isWidthAtLeastBreakpoint(600) && !isWidthAtLeastBreakpoint(840)
            val isExpandedWidth = isWidthAtLeastBreakpoint(840)

            val isCompactHeight = !isHeightAtLeastBreakpoint(480)
            val isMediumHeight = isHeightAtLeastBreakpoint(480) && !isHeightAtLeastBreakpoint(900)
            val isExpandedHeight = isHeightAtLeastBreakpoint(900)

            return when {
                isCompactWidth && (isMediumHeight || isExpandedHeight) ->
                    MOBILE_PORTRAIT

                isExpandedWidth && isCompactHeight ->
                    MOBILE_LANDSCAPE

                isMediumWidth && isExpandedHeight ->
                    TABLET_PORTRAIT

                isMediumWidth && isMediumHeight ->
                    TABLET_PORTRAIT

                isExpandedWidth && isMediumHeight ->
                    TABLET_LANDSCAPE

                isExpandedWidth && isExpandedHeight ->
                    DESKTOP

                else -> DESKTOP
            }
        }
    }
}

