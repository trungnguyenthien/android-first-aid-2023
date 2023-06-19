package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.FragmentManager
import vn.nhh.aid.R
interface BaseListFragmentController {
    fun onViewCreated(view: BaseListFragmentView)
    fun onPressBack(view: BaseListFragmentView)
    fun onResume(view: BaseListFragmentView)
}

data class BaseListFragmentView(
    val fragment: BaseListFragment,
    val header: LinearLayoutCompat,
    val body: LinearLayoutCompat,
    val footer: LinearLayoutCompat
)

class BaseListFragment(private val controller: BaseListFragmentController) : BaseFragment(), PreventBack {
    lateinit var headerLinearLayout: LinearLayoutCompat
    lateinit var footerLinearLayout: LinearLayoutCompat
    lateinit var bodyLinearLayout: LinearLayoutCompat

    private fun baseListFragmentView() = BaseListFragmentView(
        fragment = this,
        header = headerLinearLayout,
        body = bodyLinearLayout,
        footer = footerLinearLayout
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_header_list_footer, container, false)
        rootView?.let {
            headerLinearLayout = it.findViewById(R.id.linear_header)
            footerLinearLayout = it.findViewById(R.id.linear_footer)
            bodyLinearLayout = it.findViewById(R.id.linear_option)
        }
        controller.onViewCreated(baseListFragmentView())
        return rootView
    }

    override fun onResume() {
        super.onResume()
        controller.onResume(baseListFragmentView())
    }

    override fun handleBackPressed(fmanager: FragmentManager) {
        controller.onPressBack(baseListFragmentView())
    }

    override fun allowBack() = false
}