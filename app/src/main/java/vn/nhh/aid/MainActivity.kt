package vn.nhh.aid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import vn.nhh.aid.databinding.ActivityMainBinding

private var shareInstance: MainActivity? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        shareInstance = this
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.map -> {
                    replaceFragment(MapFragment())
                }
                R.id.lesson -> {
                    replaceFragment(LessonFragment())
                }
                R.id.setting -> {
                    replaceFragment(SettingFragment())
                }

            }

            true
        }
    }

    @SuppressLint("CommitTransaction")
    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }
}
