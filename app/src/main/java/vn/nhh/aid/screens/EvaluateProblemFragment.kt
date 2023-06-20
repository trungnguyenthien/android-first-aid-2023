package vn.nhh.aid.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.FragmentManager
import vn.nhh.aid.R
import vn.nhh.aid.views.ImageTextComponent

class EvaluateProblemFragment : BaseListFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Evaluate-Problem"

        headerLinearLayout.addView(ImageTextComponent(requireContext()).apply {
            setText("Na tra, ma dong tai the")
            setImageId(R.drawable.dr_black)
        })
    }

    override fun handleBackPressed(fmanager: FragmentManager) {
        super.handleBackPressed(fmanager)

    }
}