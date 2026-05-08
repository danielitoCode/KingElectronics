package com.elitec.kingelectronics.feature.auth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elitec.kingelectronics.infraestructure.presentation.util.DeviceConfiguration.Companion.toDeviceConfiguration
import kingelectronics.composeapp.generated.resources.Res
import kingelectronics.composeapp.generated.resources.compose_multiplatform
import kingelectronics.composeapp.generated.resources.king
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    val deviceConfiguration = currentWindowAdaptiveInfo().windowSizeClass.toDeviceConfiguration()
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = deviceConfiguration.name
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        /*Image(
            painter = painterResource(Res.drawable.king),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )*/
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Diseñado por : </> ELITEC"
            )
            Spacer(
                modifier = Modifier.width(10.dp)
            )
            Icon(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}