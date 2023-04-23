package ir.hasin.mani.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hasin.mani.model.dto.MovieDetailResponse
import ir.hasin.mani.model.dto.Resource
import ir.hasin.mani.model.dto.Status
import ir.hasin.mani.model.repository.MovieRepository
import ir.hasin.mani.util.Utils.getMessageError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
                _resMovieDetail.value = Resource.success(it)
            }.onFailure {
                _resMovieDetail.value = Resource.error(getMessageError(it))
            }
        }
    }

}