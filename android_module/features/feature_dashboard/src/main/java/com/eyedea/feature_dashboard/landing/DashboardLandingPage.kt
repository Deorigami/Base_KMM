package com.eyedea.feature_dashboard.landing

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.eyedea.feature_config.base.BaseComposeFragment
import com.eyedea.feature_config.base.BaseViewBindingFragment
import com.eyedea.feature_dashboard.R
import com.eyedea.feature_dashboard.databinding.DashboardLandingPageBinding
import com.eyedea.ui_component.compose.cards.WeatherInfoCard
import com.eyedea.ui_component.compose.cards.WeatherInfoCardData

class DashboardLandingPage : BaseComposeFragment() {

    override val TAG: String
        get() = "Test"

    @Composable
    override fun ComposeContent() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            WeatherInfoCard()
        }
    }


}

@Preview(
    showBackground = true,
)
@Composable
fun DashboardLandingPagePrev() {
    DashboardLandingPage().ComposeContent()
}