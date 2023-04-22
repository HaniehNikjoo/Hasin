package ir.hasin.mani.view.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import ir.hasin.mani.R
import ir.hasin.mani.common.Constants.BASE_URL_IMG_W500
import ir.hasin.mani.common.Constants.BASE_URL_IMG_ORG
import ir.hasin.mani.model.dto.ErrorResponse
import ir.hasin.mani.model.dto.MovieDetailResponse
import ir.hasin.mani.model.dto.MovieResult
import ir.hasin.mani.util.Utils
import retrofit2.HttpException

@Composable
fun MovieList(
    items: LazyPagingItems<MovieResult>,
    onItemClicked: ((String) -> Unit)? = null,
) {
    Column {
        Header(title = stringResource(R.string.popular))
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.DarkGray)
        ) {
            if (items.itemCount == 0 && items.loadState.refresh != LoadState.Loading) {
                var message: String? = null
                items.apply {
                    when {
                        loadState.refresh is LoadState.Error -> {
                            val e = loadState.refresh as LoadState.Error
                            message = Utils.getMessageError(e.error)
                        }
                        loadState.append is LoadState.Error -> {
                            val e = loadState.append as LoadState.Error
                            message = Utils.getMessageError(e.error)
                        }
                    }
                }
                Text(
                    text = message ?: stringResource(id = R.string.not_found_items),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.Center),
                    color = Color.LightGray
                )
            } else Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF222222))
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(count = 3),
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(color = Color(0xFF222222))
                        .padding(bottom = 12.dp)
                        .align(Alignment.Center),
                    content = {
                        items(items.itemCount) { index ->
                            items[index]?.let {
                                MovieItem(
                                    it, onItemClicked = onItemClicked
                                )
                            }
                        }
                    })
            }
            if (items.loadState.refresh == LoadState.Loading) CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp)
                    .align(Alignment.Center)
                    .wrapContentSize(), color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(
    item: MovieResult, onItemClicked: ((String) -> Unit)? = null
) {
    val requestBuilder = Glide.with(LocalView.current).asDrawable()
    Column(
        Modifier
            .wrapContentSize()
            .border(2.dp, Color(0xFF222222))
            .clickable {
                onItemClicked?.invoke(item.id)
            }) {
        GlideImage(
            model = BASE_URL_IMG_W500 + item.poster_path,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight(.33f)
                .fillMaxWidth()
        ) {
            it.thumbnail(
                requestBuilder.load(R.drawable.ic_place_holder)
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetail(
    item: MovieDetailResponse?, onItemClicked: (() -> Unit)
) {
    val constraints = ConstraintSet {
        val topGuideline = createGuidelineFromTop(0.22f)

        val posterBox = createRefFor("posterBox")

        constrain(posterBox) {
            top.linkTo(topGuideline)
            start.linkTo(parent.start)
        }
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        val requestBuilder = Glide.with(LocalView.current).asDrawable()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(3f)
        ) {
            GlideImage(
                model = BASE_URL_IMG_ORG + item?.backdrop_path,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight(.33f)
                    .fillMaxWidth()
            ) {
                it.thumbnail(
                    requestBuilder.load(R.drawable.ic_place_holder)
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxHeight()
                    .padding(top = 30.dp)
                    .padding(horizontal = 15.dp)
            ) {
                Image(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            onItemClicked.invoke()
                        },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color(0xFFEEEEEE))
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color(0xFFEEEEEE))
                )
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    modifier = Modifier.wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_favorite_border),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color(0xFFEEEEEE))
                )
            }
        }
        Box(
            modifier = Modifier
                .layoutId("posterBox")
                .fillMaxHeight(.3f)
                .fillMaxWidth(.4f)
                .padding(start = 15.dp)
        ) {
            GlideImage(
                model = BASE_URL_IMG_ORG + item?.poster_path,
                contentDescription = null,
                contentScale = ContentScale.None,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                it.thumbnail(
                    requestBuilder.load(R.drawable.ic_place_holder)
                )
            }
        }
    }

}
