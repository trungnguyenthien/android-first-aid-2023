package vn.nhh.aid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import vn.nhh.aid.screens.BaseFragment
import vn.nhh.aid.screens.EvaluateProblemFragment
import vn.nhh.aid.screens.PreventBack
import vn.nhh.aid.screens.TopFragment

private var shareInstance: MainActivity? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        shareInstance = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushPageStack(TopFragment())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is PreventBack) {
            fragment.handleBackPressed(fmanager = supportFragmentManager)
            if (!fragment.allowBack()) return
        }
        super.onBackPressed()
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