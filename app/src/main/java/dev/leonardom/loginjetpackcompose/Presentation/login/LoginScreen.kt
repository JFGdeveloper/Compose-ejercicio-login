package dev.leonardom.loginjetpackcompose.Presentation

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.leonardom.loginjetpackcompose.Presentation.comuns.EvenDialog
import dev.leonardom.loginjetpackcompose.Presentation.comuns.RoundedButton
import dev.leonardom.loginjetpackcompose.Presentation.comuns.TexFieldTransparent
import dev.leonardom.loginjetpackcompose.Presentation.login.LoginState
import dev.leonardom.loginjetpackcompose.R


@Composable
fun LoginScreen(
    state: LoginState, // lo voy a crear yo no existia
    onLogin: (String,String) -> Unit,
    onNavigateToRegistro: () -> Unit,
    onDissmisDialog: () -> Unit
) {

    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
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
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            ConstraintLayout {

                val (surface, fab) = createRefs()

                Surface(  ///////////surface ///////////////////////////////////////////
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

                    Column(
                        modifier = Modifier   //////////////////colum
                            .fillMaxWidth()
                            .padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Text(
                            text = "Wellcome to back!!!",
                            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Medium)

                        )

                        Text(
                            text = "Login to your account",
                            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary)

                        )

                        Column(
                            modifier = Modifier  /////////////colum
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)

                        ) {
                            TexFieldTransparent(
                                textFieldValue = emailValue,
                                textLabel = "Email",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)  // MUEVE EL FOCO HACIA ABAJO SIGUIENTE FIELDTEXT
                                }),
                                imeAction = ImeAction.Next // este llama a onNext
                            )

                            TexFieldTransparent(
                                textFieldValue = passwordValue,
                                textLabel = "Password",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(onDone = {
                                    focusManager.clearFocus()
                                    onLogin(emailValue.value,passwordValue.value)
                                }),
                                imeAction = ImeAction.Done, // este llama a onNext
                                trailingIcon = {
                                    IconButton(
                                        onClick = { passwordVisibility = !passwordVisibility }
                                    ) {
                                        Icon(
                                            imageVector = if (passwordVisibility) {
                                                Icons.Default.Visibility
                                            } else {
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Icono del Password"
                                        )

                                    }
                                },
                                visualTransformation = if (passwordVisibility) {  // esta logica muestra los puntos en la contraseña
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                            )

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.End,
                                text = "Has olvidado tu contraseña?"
                            )


                        }///////////////////////////////////colum

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            RoundedButton(
                                texto = "Login",
                                displayPogresBar = state.displayProgresBar,
                                onClick = {
                                    onLogin(emailValue.value,passwordValue.value)
                                }

                            )

                            ClickableText(
                                text = buildAnnotatedString {
                                    append("Do not you have an Account?")
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append("Sing up")
                                    }
                                },
                            ){
                                onNavigateToRegistro()
                            }


                        }//////////////////////////colum


                    }////////////////////////////////////////////colum



                    }/////////////////////////////////////////////////////////////////////surface

                    FloatingActionButton(modifier = Modifier
                        .size(72.dp)
                        .constrainAs(fab) {
                            top.linkTo(surface.top, margin = (-36).dp)
                            end.linkTo(surface.end, margin = 36.dp)
                        },
                        backgroundColor = MaterialTheme.colors.primary,
                        onClick = {
                            onNavigateToRegistro()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(42.dp),
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "icono del boton flotante",
                            tint = Color.White
                        )

                    }///////////////////////////////////FAB
                }///////////////////////////////////////////////////CONSTRAINT
            }/////////////////////////////////////////////////BOX

        if(state.errorMessages != null){
            EvenDialog(errorMessage = state.errorMessages,onDissmis = onDissmisDialog)
        }

        }///////////FUN BOX
    }////////FUN LOGIN SCREEN