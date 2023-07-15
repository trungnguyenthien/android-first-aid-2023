package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack

private const val QUESTION_ID_PARAM = "QUESTION_ID_PARAM"

class EnvironmentEvaluateFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enviroment_evaluation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val centerButton: Button = view.findViewById(R.id.skip_button)

        centerButton.setOnClickListener {
            pushPageStack(DangerlevelFragment.newInstance(""))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(questionID: String) = EnvironmentEvaluateFragment().apply {
            arguments = Bundle().apply {
                putString(QUESTION_ID_PARAM, questionID)
            }
        }
    }
}
