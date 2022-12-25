package com.eyedea.ui_component.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable fun headline900() = TextStyle(
    fontFamily = urbanistBoldFontFamily(),
    fontSize = 48.sp,
    lineHeight = 50.sp,
)
@Composable fun headline800() = TextStyle(
    fontFamily = urbanistBoldFontFamily(),
    fontSize = 36.sp,
    lineHeight = 38.sp,
)
@Composable fun headline700() = TextStyle(
    fontFamily = urbanistBoldFontFamily(),
    fontSize = 28.sp,
    lineHeight = 30.sp,
)
@Composable fun headline600() = TextStyle(
    fontFamily = urbanistBoldFontFamily(),
    fontSize = 24.sp,
    lineHeight = 26.sp,
)
@Composable fun headline500() = TextStyle(
    fontFamily = urbanistBoldFontFamily(),
    fontSize = 20.sp,
    lineHeight = 22.sp,
)
@Composable fun headline400() = TextStyle(
    fontFamily = urbanistSemiBoldFontFamily(),
    fontSize = 18.sp,
    lineHeight = 20.sp,
)
@Composable fun headline300() = TextStyle(
    fontFamily = urbanistSemiBoldFontFamily(),
    fontSize = 16.sp,
    lineHeight = 18.sp,
)
@Composable fun headline200() = TextStyle(
    fontFamily = urbanistSemiBoldFontFamily(),
    fontSize = 14.sp,
    lineHeight = 16.sp,
)
@Composable fun headline100() = TextStyle(
    fontFamily = urbanistSemiBoldFontFamily(),
    fontSize = 12.sp,
    lineHeight = 14.sp,
)
@Composable fun headline10() = TextStyle(
    fontFamily = urbanistSemiBoldFontFamily(),
    fontSize = 10.sp,
    lineHeight = 12.sp,
)

@Composable fun bodyText300() = TextStyle(
    fontFamily = urbanistRegularFontFamily(),
    fontSize = 16.sp,
    lineHeight = 12.sp,
)

@Composable fun bodyText200() = TextStyle(
    fontFamily = urbanistRegularFontFamily(),
    fontSize = 14.sp,
    lineHeight = 12.sp,
)

@Composable fun bodyText100() = TextStyle(
    fontFamily = urbanistRegularFontFamily(),
    fontSize = 12.sp,
    lineHeight = 12.sp,
)