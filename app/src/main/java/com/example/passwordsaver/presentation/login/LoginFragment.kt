package com.example.passwordsaver.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.passwordsaver.R
import com.example.passwordsaver.presentation.login.components.PinTextField
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewmodel by viewModels<LoginViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    PinTextField(
                        modifier = Modifier.fillMaxWidth(),
                        pin = viewmodel.state.value.pin,
                        onPinChange = { viewmodel.onPinChange(it) },
                        label = { Text("Enter your PIN", textAlign = TextAlign.Left) })
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Button(onClick = { findNavController().navigate(R.id.login) }) {
                            Text(text = "Log In")
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        showBiometricPrompt()
    }

    private fun showBiometricPrompt() {
        val biometricsIgnoredErrors = listOf(
            BiometricPrompt.ERROR_NEGATIVE_BUTTON,
            BiometricPrompt.ERROR_CANCELED,
            BiometricPrompt.ERROR_USER_CANCELED,
            BiometricPrompt.ERROR_NO_BIOMETRICS
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Auth required")
            .setSubtitle("Prove you are the owner of this device!")
            .setNegativeButtonText(getString(android.R.string.cancel))
            .build()

        val biometricPrompt = BiometricPrompt(
            this,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    if (errorCode !in biometricsIgnoredErrors) {
                        Toast.makeText(this@LoginFragment.context, errString, Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Toast.makeText(this@LoginFragment.context, "Auth succeeded", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    Toast.makeText(this@LoginFragment.context, "Auth failed", Toast.LENGTH_LONG)
                        .show()
                }
            }
        )

        biometricPrompt.authenticate(promptInfo)
    }
}