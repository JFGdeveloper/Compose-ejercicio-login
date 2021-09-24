package dev.leonardom.loginjetpackcompose.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Destination(
    val ruta: String,
    val arguments: List<NamedNavArgument>
){

    object login: Destination("login", emptyList())
    object registro: Destination("registro", emptyList())

    // ejem de como pasar argumentos
    object home: Destination("home",
        listOf(
            navArgument("password") { type = NavType.StringType },
            navArgument("email") { type = NavType.StringType }
        )
    )

}
