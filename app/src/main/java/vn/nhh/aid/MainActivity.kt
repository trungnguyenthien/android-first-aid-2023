package vn.nhh.aid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import vn.nhh.aid.screens.BaseFragment
import vn.nhh.aid.screens.TopFragment

private var shareInstance: MainActivity? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        shareInstance = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushPageStack(TopFragment())
    }
}

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