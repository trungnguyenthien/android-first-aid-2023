package vn.nhh.aid

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import vn.nhh.aid.screens.*


private var shareInstance: MainActivity? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        shareInstance = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = getSharedPreferences("UserInfo", MODE_PRIVATE)
        if (sharedPref.getBoolean("my_first_time", false)){ //Hien khong can
            sharedPref.edit().putBoolean("my_first_time", false).apply()
            pushPageStack(InfoFragment())
        }
        pushPageStack(HomeFragment())
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

    @SuppressLint("CommitTransaction")
    public fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()

    }

    fun call()
    {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"))
        startActivity(intent)
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

