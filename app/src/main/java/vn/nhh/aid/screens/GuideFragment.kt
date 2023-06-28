package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.nhh.aid.R

private const val GUIDE_ID_PARAM = "GUIDE_ID_PARAM"

class GuideFragment : BaseFragment() {
    private var guideId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            guideId = it.getString(GUIDE_ID_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_treatment_guide, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(guideId: String) = GuideFragment().apply {
            arguments = Bundle().apply {
                putString(GUIDE_ID_PARAM, guideId)
            }
        }
    }
}