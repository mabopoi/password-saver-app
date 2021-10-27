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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.passwordsaver.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { findNavController().navigate(R.id.login) }) {
                        Text(text = "Log In")
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                        Toast.makeText(this@LoginFragment.context, errString, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Toast.makeText(this@LoginFragment.context, "Auth succeeded", Toast.LENGTH_LONG).show()
                }

                override fun onAuthenticationFailed() {
                    Toast.makeText(this@LoginFragment.context, "Auth failed", Toast.LENGTH_LONG).show()
                }
            }
        )

        biometricPrompt.authenticate(promptInfo)
    }
}