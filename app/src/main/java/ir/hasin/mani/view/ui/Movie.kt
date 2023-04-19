package ir.hasin.mani.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.hasin.mani.R
import ir.hasin.mani.model.dto.Result

@Composable
fun MovieList(
    items: List<Result>,
    onItemClicked: ((String) -> Unit)? = null,
    isLoadingItems: Boolean = true,
//    onNextPageLoadRequest: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .background(
                Color(0xFF222222)
            )
            .fillMaxHeight()
    ) {
        if (items.isEmpty() && !isLoadingItems) Text(
            text = stringResource(id = R.string.not_found_items),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center),
            color = Color.LightGray
        )
        else Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF222222))
        ) {

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(color = Color(0xFF222222))
                    .padding(bottom = 12.dp)
                    .align(Alignment.Center),
            ) {
                items(items.size) { index ->
                    if (index < items.size) MovieItem(
                        items[index], onItemClicked = onItemClicked
                    )
                }
            }
        }
        if (isLoadingItems) CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp)
                .align(Alignment.Center)
                .wrapContentSize(),
            color = Color.White
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(
    item: ir.hasin.mani.model.dto.Result, onItemClicked: ((String) -> Unit)? = null
) {
    val requestBuilder = Glide.with(LocalView.current).asDrawable()
    Column(
        Modifier
            .wrapContentSize()
            .border(2.dp, Color(0xFF222222))
            .clickable {
                onItemClicked?.invoke(item.id)
            }
    ) {
        GlideImage(
            model = "https://image.tmdb.org/t/p/w500/${item.poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.wrapContentSize()
        ) {
            it.thumbnail(
                requestBuilder.load(android.R.drawable.ic_menu_report_image)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = item.title,
                color = Color.White,
                style = TextStyle(
                    fontFamily = iranSansFamily, fontWeight = FontWeight.Bold
                ),
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}