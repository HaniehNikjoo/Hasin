package ir.hasin.mani.model.repository

import ir.hasin.mani.model.datasource.MovieWebApiDatasource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieWebApiDatasource: MovieWebApiDatasource
) {
    suspend fun getMovieList(page: String) = movieWebApiDatasource.getMovieList(page)
}