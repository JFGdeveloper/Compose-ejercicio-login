package dev.leonardom.loginjetpackcompose.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// cramos la clase para la inyeccion de dependencias
// tenemos que llamarla en el manifest
@HiltAndroidApp
class BaseAplication: Application(){

}