package dev.leonardom.loginjetpackcompose.Presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// ejem para recibir parametros en la navegation
// para pasarlos lo declaro en Destination
@Composable
fun HomeScreen(
    email:String,
    password: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Wellcome Home",style = MaterialTheme.typography.h3)
        Text(text = "Email $email, password $password")
    }
}