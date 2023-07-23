package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.nhh.aid.R

class EdetailsFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enviromentdetails, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = EdetailsFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}