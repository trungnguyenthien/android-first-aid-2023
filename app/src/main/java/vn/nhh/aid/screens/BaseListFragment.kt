package vn.nhh.aid.screens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.FragmentManager
import vn.nhh.aid.R

open class BaseListFragment : BaseFragment(), PreventBack {
    lateinit var headerLinearLayout: LinearLayoutCompat
    lateinit var footerLinearLayout: LinearLayoutCompat
    lateinit var bodyLinearLayout: LinearLayoutCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = R.layout.fragment_header_list_footer
        return inflater.inflate(layout, container,false).also {
            it.setBackgroundColor(Color.GRAY)
            headerLinearLayout = it.findViewById(R.id.linear_header)
            footerLinearLayout = it.findViewById(R.id.linear_footer)
            bodyLinearLayout = it.findViewById(R.id.linear_option)
        }
    }

    override fun handleBackPressed(fmanager: FragmentManager) {

    }

    override fun allowBack() = false
}