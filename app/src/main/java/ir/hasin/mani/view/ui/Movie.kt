package ir.hasin.mani.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.hasin.mani.R
import ir.hasin.mani.model.dto.MovieListResponse

@Composable
fun MovieList(
    items: MovieListResponse?,
    onItemClicked: ((String) -> Unit)? = null,
    isLoadingItems: Boolean = false,
//    onNextPageLoadRequest: () -> Unit = {},
) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .background(
                Color.Gray
            )
            .fillMaxHeight()
    ) {
        if (items == null || (items.results.isEmpty() && !isLoadingItems)) Text(
            text = stringResource(id = R.string.not_found_items),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center),
            color = Color.DarkGray
        )
        else Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(color = Color.Gray)
                    .padding(bottom = 12.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = rememberLazyListState()
            ) {
                val count = items.results.size + 1
                items(count = count) { index ->
                    if (index < items.results.size) MovieItem(
                        items.results[index], onItemClicked = onItemClicked
                    )
                    else {
//                            onNextPageLoadRequest()
//                            if (isLoadingItems)
//                                CircularProgressIndicator(
//                                modifier = Modifier
//                                    .size(60.dp)
//                                    .padding(10.dp)
//                                    .align(Alignment.Center)
//                                    .wrapContentSize()
//                            )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    item: ir.hasin.mani.model.dto.Result, onItemClicked: ((String) -> Unit)? = null
) {

}