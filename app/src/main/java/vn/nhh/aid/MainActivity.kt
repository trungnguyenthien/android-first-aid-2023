package vn.nhh.aid

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import vn.nhh.aid.screens.*

private var shareInstance: MainActivity? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.title = "First Aid"
        shareInstance = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val barColor = Color.WHITE
        window.statusBarColor = barColor
        supportActionBar?.displayOptions = DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.abs_layout)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(barColor))
        supportActionBar?.customView?.findViewById<AppCompatTextView>(R.id.tvTitle)?.let {
            it.text = this.title
        }

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavView.itemIconTintList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected), // When the tab is selected.
                intArrayOf() // When the tab is not selected.
            ),
            intArrayOf(
                Color.BLACK,
                Color.GRAY
            )
        )
        bottomNavView.itemTextColor = bottomNavView.itemIconTintList

        bottomNavView.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.home_button -> {}
                R.id.location_butotn -> {}
                R.id.course_button -> {}
                R.id.account_button -> {}
                else -> {}
            }
        }

        val sharedPref = getSharedPreferences("UserInfo", MODE_PRIVATE)
        if (sharedPref.getBoolean("my_first_time", false)) { //Hien khong can
            sharedPref.edit().putBoolean("my_first_time", false).apply()
            pushPageStack(InfoFragment())
        }
        pushPageStack(HomeFragment())
//        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//                if (fragment is PreventBack) {
//                    fragment.handleBackPressed(manager = supportFragmentManager)
//                    if (!fragment.allowBack()) return
//                }
//            }
//        })

    }

    @SuppressLint("CommitTransaction")
    public fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}

fun shareMainActivity() = shareInstance!!

fun pushPageStack(fragment: BaseFragment, trackName: String? = null) {
    val fragmentManager = shareInstance?.supportFragmentManager ?: return
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragment_container, fragment)
    fragmentTransaction.addToBackStack(trackName)
    fragmentTransaction.commit()
}

fun popStack(trackName: String? = null) {
    val fragmentManager = shareInstance?.supportFragmentManager ?: return
    fragmentManager.popBackStack(trackName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}