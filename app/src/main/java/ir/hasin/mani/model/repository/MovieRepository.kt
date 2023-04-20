package ir.hasin.mani.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.hasin.mani.model.datasource.MovieWebApiDatasource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieWebApiDatasource: MovieWebApiDatasource
) {
    fun getMovieList() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            MoviePagingSource(movieWebApiDatasource)
        }
    ).flow
}