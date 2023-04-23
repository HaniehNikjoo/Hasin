package ir.hasin.mani.model.repository.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.hasin.mani.model.datasource.MovieWebApiDatasource
import ir.hasin.mani.model.dto.MovieResult

class MoviePagingSource(
    private val newsApiService: MovieWebApiDatasource,
): PagingSource<Int, MovieResult>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        return try {
            val page = params.key ?: 1
            val response = newsApiService.getMovieList(page = page)

            LoadResult.Page(
                data = response.movieResults,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.movieResults.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}