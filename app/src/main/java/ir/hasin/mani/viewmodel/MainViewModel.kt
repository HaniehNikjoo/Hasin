package ir.hasin.mani.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hasin.mani.model.datasource.MovieWebApiDatasource
import ir.hasin.mani.model.dto.MovieListResponse
import ir.hasin.mani.model.dto.Resource
import ir.hasin.mani.model.dto.Status
import ir.hasin.mani.model.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
):ViewModel() {
    private val _resMovieList =
        MutableStateFlow<Resource<MovieListResponse?>>(Resource(Status.LOADING))
    val resMovieList = _resMovieList.asStateFlow()

}