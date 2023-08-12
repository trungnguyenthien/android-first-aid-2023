package vn.nhh.aid.screens

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack


class EnvironmentEvaluateFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enviroment_evaluation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val SkipButton: Button = view.findViewById(R.id.skip_button)

        SkipButton.setOnClickListener {
            pushPageStack(DangerleveljudgeFragment.newInstance())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = EnvironmentEvaluateFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}
