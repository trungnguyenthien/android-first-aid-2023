package vn.nhh.aid.screens

import android.os.Bundle
import android.view.View
import org.json.JSONObject
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack
import vn.nhh.aid.shareMainActivity
import vn.nhh.aid.utils.makeMessageToggleButton
import vn.nhh.aid.utils.randUUID
import vn.nhh.aid.utils.readJsonFromAssets
import vn.nhh.aid.utils.toList
import vn.nhh.aid.views.ImageTextComponent

private const val QUESTION_ID_PARAM = "QUESTION_ID_PARAM"

class EvaluateProblemFragment : BaseListFragment() {
    private var questionObject: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val questionID = it.getString(QUESTION_ID_PARAM) ?: ""
            if (questionID.isBlank()) {
                questionObject = questionRoot
            } else {
                questionObject = questionRoot?.find(questionID)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Đánh giá bệnh trạng"

        val question = questionObject?.question
        if (!question.isNullOrEmpty() && question != "null") {
            val imageTextView = ImageTextComponent(requireContext()).apply {
                setText(question)
                setImageId(R.drawable.dr_black)
            }
            headerLinearLayout.addView(imageTextView)
        }

        questionObject?.options?.forEach { option ->
            val text = option.answer ?: "---"
            val button = makeMessageToggleButton(requireContext(), text)
            bodyLinearLayout.addView(button)
            button.setOnClickListener {
                val guideId = option.guideline
                val nextFragment = if (guideId.isNullOrEmpty()) {
                    newInstance(questionID = option.id)
                } else {
                    GuideFragment.newInstance(guideId = guideId)
                }
                pushPageStack(nextFragment)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(questionID: String) = EvaluateProblemFragment().apply {
            arguments = Bundle().apply {
                putString(QUESTION_ID_PARAM, questionID)
            }
        }

        val questionRoot = getQuestionRoot()
    }
}

data class Question(
    val id: String,
    val parentId: String? = null,
    val question: String? = null,
    val answer: String? = null,
    val guideline: String? = null,
    val options: List<Question> = emptyList()
) {
    fun find(id: String, questions: List<Question> = listOf(this) ): Question? {
        for(question in questions) {
            if(question.id == id) {
                return question
            }
            val resultInOptions = find(id = id, questions = question.options)
            if (resultInOptions != null) {
                return resultInOptions
            }
        }
        return null
    }
}

fun getQuestionRoot(): Question? {
    val root = readJsonFromAssets(shareMainActivity(), "question.json") ?: return null
    return parseOption(root)
}

fun parseOption(json: JSONObject, parentId: String = ""): Question {
    val myId = randUUID()
    return Question(
        id = myId,
        parentId = parentId,
        question = json.optString("question"),
        answer = json.optString("answer"),
        guideline = json.optString("guideline"),
        options = json.toList("options").map { parseOption(it, parentId = myId) }
    )
}