package ir.hasin.mani.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hasin.mani.model.dto.ErrorResponse
import ir.hasin.mani.model.dto.MovieListResponse
import ir.hasin.mani.model.dto.Resource
import ir.hasin.mani.model.dto.Status
import ir.hasin.mani.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _resMovieList =
        MutableStateFlow<Resource<MovieListResponse?>>(Resource(Status.LOADING))
    val resMovieList = _resMovieList.asStateFlow()

    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getMovieList(page = "1")
            }.onSuccess {
                _resMovieList.value = (Resource.success(it))
            }.onFailure {
                val errorResponse = Gson().fromJson(
                    (it as? HttpException)?.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                _resMovieList.value = (Resource.error(errorResponse.status_message))
            }
        }
    }

}