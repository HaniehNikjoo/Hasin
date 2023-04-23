package ir.hasin.mani.viewmodel

import androidx.lifecycle.asLiveData
import com.google.common.truth.Truth.assertThat
import ir.hasin.mani.model.datasource.MockApiImplementation
import ir.hasin.mani.model.dto.Status
import ir.hasin.mani.model.repository.MockMovieRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(MockMovieRepository(MockApiImplementation()))
    }

    @Test
    fun `test if any data is missing return error`() = runTest {


        val result = viewModel.getMovieList().asLiveData()

        assertThat(result.value).isNotNull()
    }
}