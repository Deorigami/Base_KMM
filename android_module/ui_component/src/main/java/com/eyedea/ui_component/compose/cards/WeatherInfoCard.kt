package com.eyedea.ui_component.compose.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eyedea.ui_component.R
import com.eyedea.ui_component.compose.theme.bodyText200
import com.eyedea.ui_component.compose.theme.headline300

@Composable
fun WeatherInfoCard(
    modifier: Modifier = Modifier,
    data : WeatherInfoCardData = WeatherInfoCardData.DEFAULT
) {
    ConstraintLayout(
        modifier = modifier
            .background(color = Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(10.dp))
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val (icon, infoText, infoValueText) = createRefs()

        Box(modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .constrainAs(icon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }) {
            Image(
                painter = painterResource(id = data.icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(3.dp)
                    .size(32.dp),

                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = data.infoText,
            modifier = Modifier
                .constrainAs(infoText){
                    start.linkTo(icon.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            style = headline300()
        )

        Text(
            text = data.infoTextValue,
            modifier = Modifier
                .constrainAs(infoValueText){
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            style = bodyText200()
        )

    }
}

data class WeatherInfoCardData(
    val icon : Int,
    val infoText : String,
    val infoTextValue : String
) {
    companion object {
        val DEFAULT = WeatherInfoCardData(
            icon = R.drawable.ic_umbrella, infoText = "RainFall", infoTextValue = "3cm"
        )
    }
}

@Preview
@Composable
fun WeatherInfoCardPrev() {
    WeatherInfoCard()
}