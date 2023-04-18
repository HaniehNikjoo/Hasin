package ir.hasin.mani.view

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import ir.hasin.mani.R

class MainActivity : AppCompatActivity() {
    private val navHostFragmentId = ViewCompat.generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @SuppressLint("SourceLockedOrientationActivity")
        requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val fragmentContainerView = FragmentContainerView(this).apply {
            setBackgroundColor(Color.WHITE)
        }
        fragmentContainerView.id = navHostFragmentId
        setContentView(fragmentContainerView)
        val finalHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction().replace(navHostFragmentId, finalHost)
            .setPrimaryNavigationFragment(finalHost).commit()
    }

}