package ir.hasin.mani.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ir.hasin.mani.model.dto.ErrorResponse
import ir.hasin.mani.model.dto.Status
import ir.hasin.mani.view.ui.ManiTheme
import ir.hasin.mani.view.ui.MovieList
import ir.hasin.mani.viewmodel.DetailViewModel
import ir.hasin.mani.viewmodel.MainViewModel
import retrofit2.HttpException
import java.net.UnknownHostException

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            val observeOnList by viewModel.resMovieDetail.collectAsState()
            if (observeOnList?.status == Status.SUCCESS) {
                val item = observeOnList.data
            } else if (observeOnList?.status == Status.ERROR) {
                Toast.makeText(requireContext(), observeOnList.message, Toast.LENGTH_LONG).show()
            }

            ManiTheme {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetail("594767")
    }
}