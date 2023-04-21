package ir.hasin.mani.model.datasource

import ir.hasin.mani.model.dto.MovieDetailResponse
import ir.hasin.mani.model.dto.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieWebApiDatasource {

    @GET("popular")
    suspend fun getMovieList(@Query("page") page: Int): MovieListResponse

    @GET("{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movie_id: String): MovieDetailResponse

}