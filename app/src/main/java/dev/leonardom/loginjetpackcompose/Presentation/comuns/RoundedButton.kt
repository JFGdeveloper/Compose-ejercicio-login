package dev.leonardom.loginjetpackcompose.Presentation.comuns

import android.graphics.Paint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


// logica para mi boton

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    texto: String,
    displayPogresBar: Boolean = false,
    onClick: ()-> Unit
) {
   if (!displayPogresBar){
       Button(
           onClick = onClick,
           modifier = modifier
               .width(240.dp)
               .height(50.dp),
           shape = RoundedCornerShape(50)
       ) {
           Text(
               text = texto,
               style = MaterialTheme.typography.h6.copy(color = Color.White)
           )
       }
   }else{
       CircularProgressIndicator(
           modifier = Modifier.size(50.dp),
           strokeWidth = 6.dp,
           color = MaterialTheme.colors.primary
       )
   }

}