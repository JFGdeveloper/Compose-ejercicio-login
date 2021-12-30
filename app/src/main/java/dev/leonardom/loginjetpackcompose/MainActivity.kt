package dev.leonardom.loginjetpackcompose

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.loginjetpackcompose.Presentation.LoginScreen
import dev.leonardom.loginjetpackcompose.Presentation.home.HomeScreen
import dev.leonardom.loginjetpackcompose.Presentation.login.LoginViewModel
import dev.leonardom.loginjetpackcompose.Presentation.registro.RegisterViewModel
import dev.leonardom.loginjetpackcompose.Presentation.registro.RegistroScreen
import dev.leonardom.loginjetpackcompose.navigation.Destination
import dev.leonardom.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme
import kotlinx.coroutines.delay


@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginJetpackComposeTheme {
                //LoginScreen()
                //RegistroScreen()
                val navController = rememberAnimatedNavController()

                BoxWithConstraints {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "Splash_screen"
                    ) {
                        splashScreen(navController)
                        // creo una extension para poder llamar a mi screen
                        // si no  esto no seria posible igual que pasa en firebase realtime
                        addLogin(navController)

                        addRegistro(navController)

                        addHome()


                    }
                }
            }
        }
    }
}

// creo un extension para mi AnimatedNavHost
@ExperimentalAnimationApi
fun NavGraphBuilder.addLogin(
    navController: NavHostController

) {
    composable(
        route = Destination.login.ruta,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500) // esta es la animacin

            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500) // esta es la animacin

            )

        },
        ///// para navegar hacia atras////////
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500) // esta es la animacin

            )

        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500) // esta es la animacin

            )

        }
        /////////////////////////////////////
    ) {
        // instancia para manejar el viewModel usando inyeccion de dependencias
        val viewModel: LoginViewModel = hiltViewModel()
        // parametros para pasarlos por la ruta
        val email = viewModel.state.value.email
        val password = viewModel.state.value.password

        // logica para mostrar y navegar
        if (viewModel.state.value.successLogin){

            LaunchedEffect(key1 = Unit){
                // como tengo declarado en Destination los parametros lo puedo pasar por aqui
                navController.navigate(Destination.home.ruta+"/$email"+"/$password"){
                }
            }

        }else{
            LoginScreen(
                state = viewModel.state.value,
                onLogin = viewModel::login,
                onNavigateToRegistro = {
                    navController.navigate(Destination.registro.ruta)
                },
                onDissmisDialog = viewModel::hideErrorDialog
            )
        }


    }
}


// creo un extension para mi AnimatedNavHost
// que pinta la animacin de mi screen
@ExperimentalAnimationApi
fun NavGraphBuilder.addRegistro(
    navController: NavHostController

) {
    composable(
        route = Destination.registro.ruta,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500) // esta es la animacin

            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500) // esta es la animacin

            )

        },
        ///// para navegar hacia atras////////
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500) // esta es la animacin

            )

        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500) // esta es la animacin

            )

        }
        /////////////////////////////////////
    ) {
        val viewModel: RegisterViewModel = hiltViewModel()
        RegistroScreen(
            state = viewModel.state.value,
            onRegister = viewModel::register,
            onBack = {
                // vuelve a la pantalla de atras
                navController.popBackStack()
            },
            onDismissDialog = viewModel::hideErrorDialog
        )
    }
}


// creo un extension para mi AnimatedNavHost
// no necesito el controller para el home en este caso
// y voy a dejar la animacion por defecto
@ExperimentalAnimationApi
fun NavGraphBuilder.addHome() {

    // ahora aqui capturo los parametro tambien en la ruta
    // con los nombres declarados en Destination
    composable(
        route = Destination.home.ruta+"/{email}"+"/{password}",
        arguments = Destination.home.arguments // esta linea es necesaria pero todavia no lo entiendo jejee

    ) {
        // AHORA YA SI GUARDO EN VAL LOS ARGUMENTOS
        val email = it.arguments?.getString("email") ?: ""
        val password = it.arguments?.getString("password") ?: ""
        HomeScreen(email,password)
    }
}



@ExperimentalAnimationApi
fun NavGraphBuilder.splashScreen(navController: NavController){

    composable(
        route= "Splash_screen"
    ) {

        SplashScreen(navController)
    }


}

@Composable
fun SplashScreen(navController: NavController){

    val scale = remember { Animatable(0f) }


    // AHORA PINTO LA PANTALLA DEL SCREEN
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)

    ) {
        Text(
            text = "Splash Screen ", modifier = Modifier
                .scale(scale.value)
                .align(Alignment.Center), fontSize = 40.sp, color = Color.White
        )
    }


    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.6f,
            animationSpec = tween(
                durationMillis = 1000,  // DURACION DE LA ANIMACION
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )

        delay(100)

        navController.navigate(Destination.login.ruta) {

            //popUpTo( "Splah_screen")
            popUpTo(Destination.login.ruta){ inclusive = true}
            //launchSingleTop = true


        }
    }

}
