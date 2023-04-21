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
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    fun getMovieList(): Flow<PagingData<MovieResult>> =
        repository.getMovieList().cachedIn(viewModelScope)

}