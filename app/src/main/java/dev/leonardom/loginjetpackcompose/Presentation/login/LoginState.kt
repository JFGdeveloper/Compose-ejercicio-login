package dev.leonardom.loginjetpackcompose.Presentation.login

import androidx.annotation.StringRes

// creado exclusivamente para el login
data class LoginState(
    val email: String= "",
    val password: String= "",
    val successLogin: Boolean = false,
    val displayProgresBar: Boolean = false,
    @StringRes val errorMessages: Int? = null
)
