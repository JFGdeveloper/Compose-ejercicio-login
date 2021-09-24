package dev.leonardom.loginjetpackcompose.Presentation.comuns

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SocialMediaButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: ()-> Unit,
    colorSocialMedia: Color
    ) {
    OutlinedButton(
        modifier = Modifier
            .width(280.dp)
            .height(50.dp),
        shape= RoundedCornerShape(50.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = colorSocialMedia
        ),
        border = BorderStroke(
            width = (1.5.dp),
            color = colorSocialMedia
        ),
        onClick = onClick
    ) {
        
        Text(text = text,style = MaterialTheme.typography.h6.copy(
            color = colorSocialMedia
        ))

    }

}