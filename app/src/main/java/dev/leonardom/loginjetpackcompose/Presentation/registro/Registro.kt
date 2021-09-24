package dev.leonardom.loginjetpackcompose.Presentation.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.leonardom.loginjetpackcompose.Presentation.comuns.EvenDialog
import dev.leonardom.loginjetpackcompose.Presentation.comuns.RoundedButton
import dev.leonardom.loginjetpackcompose.Presentation.comuns.SocialMediaButton
import dev.leonardom.loginjetpackcompose.Presentation.comuns.TexFieldTransparent
import dev.leonardom.loginjetpackcompose.ui.theme.FACEBOOKCOLOR
import dev.leonardom.loginjetpackcompose.ui.theme.GMAILCOLOR

@Composable
fun RegistroScreen(
    state: RegisterState,
    onRegister: (String,String,String,String,String) -> Unit,
    onBack: () -> Unit,
    onDismissDialog: () -> Unit
) {

    val nameValue = rememberSaveable { mutableStateOf("") }
    val emailValue = rememberSaveable { mutableStateOf("") }
    val phoneValue = rememberSaveable { mutableStateOf("") }
    val passwordValues = rememberSaveable { mutableStateOf("") }
    val comfirPasswordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirPasswordVisibility by remember { mutableStateOf(false) }
    val focus = LocalFocusManager.current

// Content
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ) {
// header
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "icono de vuelta",
                        tint = MaterialTheme.colors.primary  // para comprobar si pone el primary por defecto
                    )

                }

                Text(
                    text = "Crear una cuenta",
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary)
                )
            }////////////////////////row header

// TextFields para registro
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TexFieldTransparent(
                    textFieldValue = nameValue,
                    textLabel = "Name",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Down) // mueve el foco hacia abajo
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TexFieldTransparent(
                    textFieldValue = phoneValue,
                    textLabel = "Phone",
                    maxChar = 10,
                    keyboardType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Down) // mueve el foco hacia abajo
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TexFieldTransparent(
                    textFieldValue = emailValue,
                    textLabel = "Email",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Down) // mueve el foco hacia abajo
                        }
                    ),
                    imeAction = ImeAction.Next

                )

                TexFieldTransparent(
                    textFieldValue = passwordValues,
                    textLabel = "Password",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Down) // mueve el foco hacia abajo
                        }
                    ),
                    imeAction = ImeAction.Next,
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

                TexFieldTransparent(
                    textFieldValue = comfirPasswordValue,
                    textLabel = "Comfirmar Password",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focus.clearFocus()
                            onRegister(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value,
                                passwordValues.value,
                                comfirPasswordValue.value
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
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

                Spacer(modifier = Modifier.height(16.dp))

                // BOTON PARA REGISTRO
                RoundedButton(
                    texto = "Sing up",
                    displayPogresBar = state.displayProgresBar,
                    onClick = {
                        onRegister(
                            nameValue.value,
                            emailValue.value,
                            phoneValue.value,
                            passwordValues.value,
                            comfirPasswordValue.value
                        )

                    }
                )

// texto cliclable para la cuenta o el login
                ClickableText(text = buildAnnotatedString {
                    append("Alrady have an Account?")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Login")
                    }
                }, onClick = { onBack()})

            }/////////colum TextFIELD

            Spacer(modifier = Modifier.height(16.dp))

// text or login
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )

                    Text(modifier= Modifier
                        .padding(8.dp),
                        text = "OR",
                        style = MaterialTheme.typography.h6.copy(  // el usa material
                            fontWeight = FontWeight.Black
                        )
                    )

                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }// row OR LOGIN WITH

                Text(
                    text = "Login with",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1.copy(
                        MaterialTheme.colors.primary
                    )
                )
            }///////column text or login

            Spacer(modifier = Modifier.height(16.dp))

// botones redes sociales
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SocialMediaButton(
                    text = "Login with Facebook",
                    colorSocialMedia = FACEBOOKCOLOR,
                    onClick = {})
                SocialMediaButton(
                    text = "Login with Gmail",
                    colorSocialMedia = GMAILCOLOR,
                    onClick = {})

            }////////////////columna botones redes sociales


        }///////column Screen
    }///////Box Screen

// logica para mostrar los errores en Dialog
    if (state.errorMessages != null){
        EvenDialog(errorMessage = state.errorMessages, onDissmis = onDismissDialog)
    }
}///funScreen