package ir.hasin.mani.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.hasin.mani.model.datasource.MovieWebApiDatasource
import ir.hasin.mani.model.repository.pagination.MoviePagingSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieWebApiDatasource: MovieWebApiDatasource
): MovieRepository {
    override suspend fun getMovieDetail(movie_id: String) = movieWebApiDatasource.getMovieDetail(movie_id)

    override fun getMovieList() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            MoviePagingSource(movieWebApiDatasource)
        }
    ).flow
}