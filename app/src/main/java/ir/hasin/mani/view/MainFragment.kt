package ir.hasin.mani.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.hasin.mani.R
import ir.hasin.mani.model.dto.MovieListResponse
import ir.hasin.mani.model.dto.Status
import ir.hasin.mani.view.ui.Header
import ir.hasin.mani.view.ui.ManiTheme
import ir.hasin.mani.view.ui.MovieList
import ir.hasin.mani.viewmodel.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    var item: MovieListResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            ManiTheme {
                val observeOnList by viewModel.resMovieList.collectAsState()
                if (observeOnList?.status == Status.SUCCESS) {
                    item = observeOnList.data
                }else  if (observeOnList?.status == Status.ERROR) {
                    Toast.makeText(requireContext(),observeOnList.message,Toast.LENGTH_LONG).show()
                }
                Column() {
                    Header(title = getString(R.string.popular))
                    MovieList(items = item,
                        isLoadingItems = observeOnList.status == Status.LOADING,
                        onItemClicked = {
//                    val bundle = Bundle()
//                    bundle.putString(Constants.BUNDLE_ID, it)
//                    NavHostFragment.findNavController(this@PeriodicSupportFragment).navigate(R.id.action_nav_periodic_support_page_fragment_to_nav_detail_page_fragment,bundle)
                        },
//                        onNextPageLoadRequest = { viewModel.loadNextPage() }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieList()
    }
}