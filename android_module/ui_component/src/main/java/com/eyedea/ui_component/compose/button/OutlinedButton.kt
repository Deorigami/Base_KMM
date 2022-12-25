package com.eyedea.ui_component.compose.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eyedea.ui_component.compose.theme.PrimarySuccess600
import com.eyedea.ui_component.compose.theme.headline300
import com.eyedea.ui_component.compose.theme.primaryBlack200
import com.eyedea.ui_component.compose.theme.primarySuccess600

object OutlinedButton {
    @Composable
    fun Medium(modifier: Modifier = Modifier, onClick : () -> Unit, text : String){
        OutlinedButton(
            onClick = { onClick.invoke() },
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            border = BorderStroke(1.dp, primaryBlack200())
        ) {
            Text(text = text, style = headline300(), color = PrimarySuccess600)
        }
    }


}

@Preview
@Composable
fun MediumPreview(){
    OutlinedButton.Medium(onClick = { /*TODO*/ }, text = "See Details")
}