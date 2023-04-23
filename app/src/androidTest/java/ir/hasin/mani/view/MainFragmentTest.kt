package ir.hasin.mani.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.hasin.mani.R
import ir.hasin.mani.view.ui.ManiTheme
import ir.hasin.mani.view.ui.MovieItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.setContent {
            ManiTheme {
                MovieItem()
            }
        }
    }

    @Test
    fun clickMovieItemsList_navigateToShowDetailFragment() {
        val navController = mock(NavController::class.java)
        val titleScenario = launchFragmentInContainer<MainFragment>()

        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        composeTestRule.onNodeWithTag("MovieItem").performClick()

        verify(navController).navigate(
            R.id.action_nav_main_fragment_to_nav_detail_fragment
        )

    }
}