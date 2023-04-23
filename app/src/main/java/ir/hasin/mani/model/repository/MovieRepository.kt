package ir.hasin.mani.model.repository

import androidx.paging.PagingData
import ir.hasin.mani.model.dto.MovieDetailResponse
import ir.hasin.mani.model.dto.MovieResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieDetail(movie_id: String) : MovieDetailResponse
    fun getMovieList() : Flow<PagingData<MovieResult>>
}