package dev.leonardom.loginjetpackcompose.Presentation.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.leonardom.loginjetpackcompose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    // manejamos el stado aqui
    val state: MutableState<LoginState> = mutableStateOf(LoginState())

    // y ahora manejamos la logica con el estado
    // para mostrar mensajes por ejem
    fun login(email: String, password: String){

        val errorMessage = if (email.isBlank() || password.isBlank()){
            R.string.error_input_empty
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            R.string.error_not_a_valid_email
        }else if (email != "user@gmail.com" || password != "password"){
            // harcodeo el codigo
            R.string.error_invalid_credentials
        }else null

        // reviso si la variable es nula
        errorMessage?.let {
            state.value = state.value.copy(errorMessages = it)
            return
        }


        // si todo lo introducido esta bien..
        // empiezo a mostrar el progresbar..
        // le paso el email y password correctos..
        // apago el progresbar
        // login lo doy por bueno sucessfull
        viewModelScope.launch {
            state.value = state.value.copy(displayProgresBar = true)
            delay(3000)
            state.value = state.value.copy(email = email,password = password)
            state.value = state.value.copy(displayProgresBar = false)
            state.value = state.value.copy(successLogin = true)
        }
    }

    // damos un valor null para ocultar el Dialog
    fun hideErrorDialog(){
        state.value = state.value.copy(
            errorMessages = null
        )
    }
}