package ir.hasin.mani.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.hasin.mani.model.datasource.MockApiImplementation
import ir.hasin.mani.model.dto.MovieDetailResponse
import ir.hasin.mani.model.dto.MovieResult
import ir.hasin.mani.model.repository.pagination.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MockMovieRepository(
    private val mockApiImplementation: MockApiImplementation
) : MovieRepository {

    override suspend fun getMovieDetail(movie_id: String): MovieDetailResponse {
        return mockApiImplementation.getMovieDetail(movie_id)
    }

    override fun getMovieList() = Pager(config = PagingConfig(
        pageSize = 20,
    ), pagingSourceFactory = {
        MoviePagingSource(mockApiImplementation)
    }).flow

}