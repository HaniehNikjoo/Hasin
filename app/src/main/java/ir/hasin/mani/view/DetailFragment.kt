package ir.hasin.mani.view

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.hasin.mani.common.Constants.BUNDLE_ID
import ir.hasin.mani.model.dto.MovieDetailResponse
import ir.hasin.mani.model.dto.Status
import ir.hasin.mani.view.ui.ManiTheme
import ir.hasin.mani.view.ui.MovieDetail
import ir.hasin.mani.viewmodel.DetailViewModel


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private var item: MovieDetailResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            val observeOnList by viewModel.resMovieDetail.collectAsState()
            if (observeOnList.status == Status.SUCCESS) {
                item = observeOnList.data
            } else if (observeOnList.status == Status.ERROR) {
                Toast.makeText(requireContext(), observeOnList.message, Toast.LENGTH_LONG).show()
            }

            ManiTheme {
                MovieDetail(item = item, onItemClicked = {
                    findNavController().popBackStack()
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getString(BUNDLE_ID)
        id?.let {
            viewModel.getMovieDetail(id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val w: Window = requireActivity().window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.view?.isFocusableInTouchMode = true
        this.view?.requestFocus()
        this.view?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    findNavController().popBackStack()
                    return true
                }
                return false
            }
        })
    }
}