package ir.hasin.mani.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hasin.mani.model.dto.*
import ir.hasin.mani.model.repository.MovieRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    fun getMovieList(): Flow<PagingData<MovieResult>> =
        repository.getMovieList().cachedIn(viewModelScope)

}