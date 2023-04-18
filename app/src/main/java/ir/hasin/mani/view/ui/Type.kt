package ir.hasin.mani.view.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ir.hasin.mani.R

// Set of Material typography styles to start with
val iransansFamily = FontFamily(
    Font(R.font.iranyekanmobilethin, FontWeight.Thin),
    Font(R.font.iranyekanmobilelight, FontWeight.Light),
    Font(R.font.iranyekanmobileregular, FontWeight.Normal),
    Font(R.font.iranyekanmobilemedium, FontWeight.Medium),
    Font(R.font.iranyekanmobilebold, FontWeight.Bold),
)

val Typography = Typography(
    defaultFontFamily = iransansFamily
)