package com.example.passwordsaver.presentation.login.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.passwordsaver.R

@Composable
fun PinTextField(
    modifier: Modifier,
    pin: String,
    onPinChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
) {
    val isPasswordVisible = rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = modifier,
        value = pin,
        label = label,
        onValueChange = onPinChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = if (isPasswordVisible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible.value = !isPasswordVisible.value },
            ) {
                Crossfade(
                    targetState = isPasswordVisible,
                ) { visible ->
                    Icon(
                        painter = painterResource(
                            id = if (visible.value) {
                                R.drawable.ic_visibility_on
                            } else {
                                R.drawable.ic_visibility_off
                            }
                        ),
                        contentDescription = "Button to show or hide your PIN",
                    )
                }
            }
        }
    )
}