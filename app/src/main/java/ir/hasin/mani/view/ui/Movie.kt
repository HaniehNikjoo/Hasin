package ir.hasin.mani.view.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.hasin.mani.R
import ir.hasin.mani.common.Constants.BASE_URL_IMG_ORG
import ir.hasin.mani.common.Constants.BASE_URL_IMG_W500
import ir.hasin.mani.model.dto.Genre
import ir.hasin.mani.model.dto.MovieDetailResponse
import ir.hasin.mani.model.dto.MovieResult
import ir.hasin.mani.util.Utils
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieList(
    data: Flow<PagingData<MovieResult>>,
    onItemClicked: ((String) -> Unit)? = null,
) {
    val listState: LazyGridState = rememberLazyGridState()
    val items: LazyPagingItems<MovieResult> = data.collectAsLazyPagingItems()
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
                LazyVerticalGrid(state = listState,
                    columns = GridCells.Fixed(count = 3),
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
            if (items.itemCount == 0 && items.loadState.refresh == LoadState.Loading) CircularProgressIndicator(
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
                    fontWeight = FontWeight.Bold
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
    item: MovieDetailResponse?, onItemClicked: (() -> Unit), isLoading: Boolean
) {
    val constraints = ConstraintSet {
        val topGuideline = createGuidelineFromTop(0.25f)
        val topGuideline2 = createGuidelineFromTop(0.32f)

        val posterBox = createRefFor("posterBox")
        val textBox = createRefFor("textBox")
        val infoBox = createRefFor("infoBox")

        constrain(posterBox) {
            top.linkTo(topGuideline)
            start.linkTo(parent.start)
        }
        constrain(textBox) {
            top.linkTo(topGuideline2)
            start.linkTo(posterBox.end)
        }
        constrain(infoBox) {
            top.linkTo(posterBox.bottom)
            start.linkTo(parent.start)
        }
    }

    Box(Modifier.fillMaxSize()) {
        ConstraintLayout(constraints, modifier = Modifier.matchParentSize()) {
            val requestBuilder = Glide.with(LocalView.current).asDrawable()
            val requestBuilder2 = Glide.with(LocalView.current).asDrawable()
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
                        requestBuilder2.load(R.drawable.ic_place_holder_backdrop)
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
                    model = BASE_URL_IMG_W500 + item?.poster_path,
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

            Column(
                modifier = Modifier
                    .layoutId("textBox")
                    .fillMaxWidth(.5f)
                    .padding(top = 15.dp, start = 15.dp)
            ) {
                Text(
                    text = item?.title ?: "", color = Color.Black, style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ), fontSize = 16.sp, modifier = Modifier.wrapContentSize()
                )
                LazyVerticalGrid(columns = GridCells.Adaptive(70.dp),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    content = {
                        items(item?.genres?.size ?: 0) { index ->
                            item?.genres?.get(index)?.let {
                                GenreItem(
                                    it
                                )
                            }
                        }
                    })
            }

            Column(
                modifier = Modifier.layoutId("infoBox")
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .background(Color.LightGray)
                )

                Row(
                    Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(.9f)
                        .align(CenterHorizontally)
                ) {
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(top = 5.dp)
                    ) {
                        Row(Modifier.align(CenterHorizontally)) {
                            Text(
                                text = item?.vote_average ?: "",
                                color = Color.DarkGray,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                fontSize = 12.sp,
                                modifier = Modifier.align(CenterVertically),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_star_rate),
                                modifier = Modifier
                                    .size(18.dp)
                                    .align(CenterVertically),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.DarkGray)
                            )
                        }
                        Text(
                            text = "${item?.vote_count ?: ""} votes ",
                            color = Color.Gray,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            ),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 5.dp)
                        )
                    }
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(top = 5.dp)
                    ) {
                        Row(Modifier.align(CenterHorizontally)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_language),
                                modifier = Modifier
                                    .size(18.dp)
                                    .align(CenterVertically),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.DarkGray)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = item?.original_language ?: "",
                                color = Color.DarkGray,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                fontSize = 12.sp,
                                modifier = Modifier.align(CenterVertically),
                                textAlign = TextAlign.Center
                            )
                        }
                        Text(
                            text = "Language",
                            color = Color.Gray,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            ),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 5.dp)
                        )
                    }
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(top = 5.dp)
                    ) {
                        Row(Modifier.align(CenterHorizontally)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_av_timer),
                                modifier = Modifier
                                    .size(18.dp)
                                    .align(CenterVertically),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.DarkGray)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = item?.release_date ?: "",
                                color = Color.DarkGray,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                fontSize = 12.sp,
                                modifier = Modifier.align(CenterVertically),
                                textAlign = TextAlign.Center
                            )
                        }
                        Text(
                            text = "Release date",
                            color = Color.Gray,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            ),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 5.dp)
                        )
                    }
                }

                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .background(Color.LightGray)
                )

                Text(
                    text = "Overview",
                    color = Color.DarkGray,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(top = 30.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                )
                Column(
                    Modifier
                        .fillMaxHeight(.3f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = item?.overview ?: "", color = Color.Gray, style = TextStyle(
                            fontWeight = FontWeight.Normal
                        ), fontSize = 12.sp, modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
        if (isLoading) CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center), color = Color.DarkGray
        )
    }
}

@Composable
fun GenreItem(item: Genre) {
    Text(
        text = item.name,
        color = Color.Gray,
        style = TextStyle(
            fontWeight = FontWeight.Bold
        ),
        fontSize = 12.sp,
        modifier = Modifier
            .border(
                1.dp, Color.Gray, RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 3.dp, vertical = 3.dp)
            .wrapContentWidth(),
        textAlign = TextAlign.Center

    )
}
