package vn.nhh.aid.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import vn.nhh.aid.R
import vn.nhh.aid.utils.makeMessageToggleButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EvaluateProblemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EvaluateProblemFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private fun questionLayout() = view?.findViewById<LinearLayoutCompat>(R.id.linear_option)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionLayout()?.let {
            it.addView(makeMessageToggleButton(requireContext(), "HELLO 1"))
            it.addView(makeMessageToggleButton(requireContext(), "HELLO 2"))
            it.addView(makeMessageToggleButton(requireContext(), "HELLO 3"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluate_problem, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(articleId: String, param2: String) =
            EvaluateProblemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, articleId)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}