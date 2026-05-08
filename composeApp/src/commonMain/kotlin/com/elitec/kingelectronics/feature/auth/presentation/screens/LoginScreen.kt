package com.elitec.kingelectronics.feature.auth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elitec.kingelectronics.infraestructure.presentation.theme.KingElectronicsTheme
import com.elitec.kingelectronics.infraestructure.presentation.util.DeviceConfiguration
import com.elitec.kingelectronics.infraestructure.presentation.util.DeviceConfiguration.Companion.toDeviceConfiguration
import kingelectronics.composeapp.generated.resources.Res
import kingelectronics.composeapp.generated.resources.king
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val deviceConfiguration = currentWindowAdaptiveInfo().windowSizeClass.toDeviceConfiguration()
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (deviceConfiguration) {
            DeviceConfiguration.DESKTOP, DeviceConfiguration.TABLET_LANDSCAPE -> {
                Row(modifier = Modifier.fillMaxSize()) {
                    // Left Side: Branding/Image
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        BrandingContent(showLargeLogo = true)
                    }

                    // Right Side: Login Form
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .verticalScroll(scrollState),
                        contentAlignment = Alignment.Center
                    ) {
                        LoginForm(modifier = Modifier.widthIn(max = 400.dp))
                    }
                }
            }
            else -> {
                // Mobile and Tablet Portrait
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    BrandingContent(showLargeLogo = false)
                    Spacer(modifier = Modifier.height(32.dp))
                    LoginForm(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun BrandingContent(showLargeLogo: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(32.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.king),
            contentDescription = "King Electronics Logo",
            modifier = Modifier.size(if (showLargeLogo) 280.dp else 120.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "King Electronics",
            style = MaterialTheme.typography.headlineLarge,
            color = if (showLargeLogo) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "La mejor tecnología a tu alcance",
            style = MaterialTheme.typography.bodyLarge,
            color = if (showLargeLogo) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "Inicia sesión para continuar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.Start).padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            placeholder = { Text("ejemplo@correo.com") },
            modifier = Modifier.fillMaxWidth(),
            //leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            //leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            /*trailingIcon = {
                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                }
            },*/
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            text = "¿Olvidaste tu contraseña?",
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
                .clickable { /* Handle forgot password */ },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Handle Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "¿No tienes una cuenta? ", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Regístrate",
                modifier = Modifier.clickable { /* Handle Sign Up */ },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    KingElectronicsTheme {
        LoginScreen()
    }
}
