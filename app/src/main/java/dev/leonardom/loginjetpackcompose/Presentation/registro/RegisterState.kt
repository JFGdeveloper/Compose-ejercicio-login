package dev.leonardom.loginjetpackcompose.Presentation.registro

import androidx.annotation.StringRes

data class RegisterState(
    val sucessRegisterState: Boolean = false,
    val displayProgresBar: Boolean = false,
    @StringRes val errorMessages: Int? = null
)
