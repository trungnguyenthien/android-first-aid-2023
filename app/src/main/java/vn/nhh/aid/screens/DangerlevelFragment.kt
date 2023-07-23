package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.nhh.aid.R

private const val D = 0

class DangerlevelFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dangerlevel_low_high, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(Judge: Int) = DangerlevelFragment().apply {
            arguments = Bundle().apply{
                putInt(D.toString(), Judge)
            }
        }
    }
}