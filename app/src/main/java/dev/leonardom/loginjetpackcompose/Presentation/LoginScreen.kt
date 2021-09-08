package dev.leonardom.loginjetpackcompose.Presentation

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.leonardom.loginjetpackcompose.Presentation.comuns.TexFieldTransparent
import dev.leonardom.loginjetpackcompose.R

@Composable
fun LoginScreen() {
    
    val emailValue = rememberSaveable{ mutableStateOf("")}
    val passwordValue = rememberSaveable{ mutableStateOf("")}
    val passwordVisibility by remember { mutableStateOf(false)}
    val focusManager = LocalFocusManager.current
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LOGO IMAGE",
            contentScale = ContentScale.Inside
        )

        Box(
           modifier= Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            ConstraintLayout {

                val (surface, fab) = createRefs()

                Surface(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .constrainAs(surface) {  // esta linea une la parte inferior del surface con el Box inferior

                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topEnd = 8.dp,
                        topStart = 8.dp
                    ) // posiblemente sean %
                ) {

                    Column(modifier = Modifier   //////////////////colum
                        .fillMaxWidth()
                        .padding(16.dp),verticalArrangement = Arrangement.SpaceEvenly) {

                        Text(
                            text = "Wellcome to back!!!",
                            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Medium)

                        )

                        Text(
                            text = "Login to your account",
                            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary)

                        )

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)

                            ) {
                            TexFieldTransparent(
                                textFieldValue = emailValue,
                                textLabel = "Email",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(
                                    FocusDirection.Down)}),
                                imeAction = ImeAction.Next // este llama a onNext
                            )

                            Text(text = "hola javia")

                        }///////////////////////////////////colum
                        
                        
                    }////////////////////////////////////////////colum

                }

                FloatingActionButton(modifier = Modifier
                    .size(72.dp)
                    .constrainAs(fab) {
                        top.linkTo(surface.top, margin = (-36).dp)
                        end.linkTo(surface.end, margin = 36.dp)
                    },
                    backgroundColor= MaterialTheme.colors.primary,
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "icono del boton flotante",
                        tint = Color.White
                    )

                }
            }
        }

    }
}