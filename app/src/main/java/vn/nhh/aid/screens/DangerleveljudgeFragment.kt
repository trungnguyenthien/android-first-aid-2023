package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack


class DangerleveljudgeFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dangerlevel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val UnknownButton: Button = view.findViewById(R.id.unknownd_button)
        val HighDanger: Button = view.findViewById(R.id.highd_button)
        val LowDanger: Button = view.findViewById(R.id.lowd_button)

        UnknownButton.setOnClickListener {
            pushPageStack(EvaluateProblemFragment.newInstance(""))
        }
        HighDanger.setOnClickListener {
            pushPageStack(DangerlevelFragment.newInstance(0))
        }
        LowDanger.setOnClickListener {
            pushPageStack(DangerlevelFragment.newInstance(1))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =  DangerleveljudgeFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}

