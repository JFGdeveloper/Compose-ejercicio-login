package dev.leonardom.loginjetpackcompose.Presentation.registro

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.leonardom.loginjetpackcompose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    // manejamos el stado aqui
    val state: MutableState<RegisterState> = mutableStateOf(RegisterState())

    // y ahora manejamos la logica con el estado
    // para mostrar mensajes por ejem
    fun register(name: String,email: String,phone: String, password: String,confirPassword: String){

        val errorMessage = if (name.isBlank()|| email.isBlank() || phone.isBlank() || password.isBlank() || confirPassword.isBlank()){
            R.string.error_input_empty
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            R.string.error_not_a_valid_email
        }else if (!Patterns.PHONE.matcher(phone).matches()){
            // harcodeo el codigo
            R.string.error_not_a_valid_phone_number
        }else if (password != confirPassword){
            R.string.error_incorrectly_repeated_password

        }else null

        // reviso si la variable es nula
        errorMessage?.let {
            state.value = state.value.copy(errorMessages = it)
            return
        }


        // si todo lo introducido esta bien..
        // empiezo a mostrar el progresbar..
        // apago el progresbar
        viewModelScope.launch {
            state.value = state.value.copy(displayProgresBar = true)
            delay(3000)
            state.value = state.value.copy(displayProgresBar = false)
        }
    }

    // damos un valor null para ocultar el Dialog
    fun hideErrorDialog(){
        state.value = state.value.copy(
            errorMessages = null
        )
    }
}