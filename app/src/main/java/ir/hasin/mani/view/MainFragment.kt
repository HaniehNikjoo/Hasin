package ir.hasin.mani.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import ir.hasin.mani.R
import ir.hasin.mani.model.dto.MovieResult
import ir.hasin.mani.view.ui.ManiTheme
import ir.hasin.mani.view.ui.MovieList
import ir.hasin.mani.viewmodel.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var movies: LazyPagingItems<MovieResult>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            movies = viewModel.getMovieList().collectAsLazyPagingItems()
            ManiTheme {
                MovieList(
                    items = movies,
                    onItemClicked = {
                        val bundle = Bundle()
                        bundle.putString("ID", it)
                        NavHostFragment.findNavController(this@MainFragment)
                            .navigate(R.id.action_nav_main_fragment_to_nav_detail_fragment, bundle)
                    },
                )
            }
        }
    }
}