//package ir.hasin.mani.model.repository.pagination
//
//import androidx.paging.*
//import com.google.common.truth.Truth.assertThat
//import ir.hasin.mani.model.datasource.FakeApiImplementation
//import ir.hasin.mani.model.dto.MovieListResponse
//import ir.hasin.mani.model.dto.MovieResult
//import junit.framework.Assert.assertTrue
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.test.runBlockingTest
//import kotlinx.coroutines.test.runTest
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class MoviePagingSourceTest {
//    lateinit var fakeMovieListData: MovieListResponse
//    private val mockApi = FakeApiImplementation()
//    private val getFakeData = runBlockingTest {
//        fakeMovieListData = mockApi.getMovieList(1)
//    }
//    private val fakeMoviesDataArticle = fakeMovieListData.movieResults
//
//
//    @Test
//    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
//        // Add mock results for the API to return.
//        fakeMoviesDataArticle.forEach { post -> mockApi.addPost(post) }
//        val remoteMediator = PageKeyedRemoteMediator(
//            mockDb,
//            mockApi,
//            SubRedditViewModel.DEFAULT_SUBREDDIT
//        )
//        val pagingState = PagingState<Int, RedditPost>(
//            listOf(),
//            null,
//            PagingConfig(10),
//            10
//        )
//        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
//        assertTrue { result is MediatorResult.Success }
//        assertFalse { (result as MediatorResult.Success).endOfPaginationReached }
//    }
//
//
//    @Test
//    fun `check Paging Source default search`() = runBlockingTest {
//
//        val pagingSource = MoviePagingSource(mockApi)
//
//        assertThat(
//            PagingSource.LoadResult.Page(
//                data = listOf(fakeMoviesDataArticle[0], fakeMoviesDataArticle[1]),
//                prevKey = null,
//                nextKey = 2
//            )
//        ).isEqualTo(
//            pagingSource.load(
//                PagingSource.LoadParams.Refresh(
//                    key = null,
//                    loadSize = 2,
//                    placeholdersEnabled = false
//                )
//            )
//        )
//
//
//    }
//
//    @Test
//    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
//        val pagingState = PagingState<Int, MovieResult>(
//            listOf(),
//            null,
//            PagingConfig(10),
//            10
//        )
//        val result = LoadState.load(LoadType.REFRESH, pagingState)
//        assertTrue { result is RemoteMediator.MediatorResult.Success }
//        assertTrue { (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached }
//    }
//
//
//
//
//
//}