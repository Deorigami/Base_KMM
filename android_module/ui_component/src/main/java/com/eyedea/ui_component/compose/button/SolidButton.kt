package com.eyedea.ui_component.compose.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eyedea.ui_component.compose.theme.*
import com.eyedea.ui_component.compose.theme.primarySuccess600

object SolidButton {
    @Composable
    fun Medium(modifier: Modifier = Modifier, onClick : () -> Unit, text : String){
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = primarySuccess600(),
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
            )
        ) {
            Text(text = text, style = headline300(), color = Color.White)
        }
    }
    @Composable
    fun Regular(modifier: Modifier = Modifier, onClick : () -> Unit, text : String){
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = primarySuccess600(),
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
            )
        ) {
            Text(text = text, style = headline300(), color = Color.White)
        }
    }
}