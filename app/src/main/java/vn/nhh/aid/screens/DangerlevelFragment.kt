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

class DangerlevelFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dangerlevel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val UnknownButton: Button = view.findViewById(R.id.unknownd_button)

        UnknownButton.setOnClickListener {
            pushPageStack(EvaluateProblemFragment.newInstance(""))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(questionID: String) =  DangerlevelFragment().apply {
            arguments = Bundle().apply {
                putString(QUESTION_ID_PARAM, questionID)
            }
        }
    }
}

