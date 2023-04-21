package ir.hasin.mani.view.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hasin.mani.R

@Composable
fun Header(title: String) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(Modifier.background(Color(0xFF222222))) {
            Row(Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
                Text(
                    text = title,
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = iranSansFamily,
                        fontWeight = FontWeight.Bold
                    ),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_home_toolbar),
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}
