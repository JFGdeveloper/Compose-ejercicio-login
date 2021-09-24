package dev.leonardom.loginjetpackcompose.Presentation.comuns

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EvenDialog(
    modifier: Modifier = Modifier,
    @StringRes errorMessage: Int,
    onDissmis: (()-> Unit)? = null
) {
    AlertDialog(
        modifier = modifier
            .background(Color.White)
            .padding(16.dp),
        onDismissRequest = {onDissmis?.invoke()},
        title = {
            Text(
                text = "erro",
                style = TextStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Text(
                text = LocalContext.current.getString(errorMessage),
                style = TextStyle(
                    color = MaterialTheme.colors.surface,
                    fontSize = 16.sp
                )
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {onDissmis?.invoke()}){
                    Text(text = "Aceptar",style= MaterialTheme.typography.button)
                }
            }
        }

    )


}