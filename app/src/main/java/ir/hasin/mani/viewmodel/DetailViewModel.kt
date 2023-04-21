package ir.hasin.mani.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hasin.mani.model.dto.*
import ir.hasin.mani.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _resMovieDetail =
        MutableStateFlow<Resource<MovieDetailResponse?>>(Resource(Status.LOADING))
    val resMovieDetail = _resMovieDetail.asStateFlow()

    fun getMovieDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getMovieDetail(id)
            }.onSuccess {
                _resMovieDetail.value = (Resource.success(it))
            }.onFailure {
                val errorResponse = Gson().fromJson(
                    (it as? HttpException)?.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                _resMovieDetail.value =
                    (Resource.error(errorResponse?.status_message ?: it.message))
            }
        }
    }

}