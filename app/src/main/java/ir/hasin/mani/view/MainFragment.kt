package ir.hasin.mani.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ir.hasin.mani.model.dto.ErrorResponse
import ir.hasin.mani.view.ui.ManiTheme
import ir.hasin.mani.view.ui.MovieList
import ir.hasin.mani.viewmodel.MainViewModel
import retrofit2.HttpException
import java.net.UnknownHostException

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            val movies = viewModel.getMovieList().collectAsLazyPagingItems()
            ManiTheme {
                MovieList(
                    items = movies,
                    onItemClicked = {
//                    val bundle = Bundle()
//                    bundle.putString(Constants.BUNDLE_ID, it)
//                    NavHostFragment.findNavController(this@PeriodicSupportFragment).navigate(R.id.action_nav_periodic_support_page_fragment_to_nav_detail_page_fragment,bundle)
                    },
                )
            }
        }
    }
}