package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack


class DangerLevelJudgeFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dangerlevel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.highd_button).setOnClickListener {
            pushPageStack(DangerLevelFragment.newInstance(0, -1))
        }
        view.findViewById<AppCompatButton>(R.id.lowd_button).setOnClickListener {
            pushPageStack(DangerLevelFragment.newInstance(1, -1))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =  DangerLevelJudgeFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}

