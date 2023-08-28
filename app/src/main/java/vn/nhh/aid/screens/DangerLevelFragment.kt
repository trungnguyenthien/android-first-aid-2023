package vn.nhh.aid.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack
import vn.nhh.aid.shareMainActivity
import vn.nhh.aid.utils.readJsonFromAssets
import vn.nhh.aid.utils.toList
import vn.nhh.aid.views.RecyclerViewAdapter

class DangerLevelFragment : BaseFragment(), RecyclerViewAdapter.OnItemClickListener {
    private lateinit var recyclerview: RecyclerView
    private var option: Option? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dangerlevel_low_high, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: TextView = view.findViewById(R.id.textView)
        title.text = option?.question

        recyclerview = view.findViewById(R.id.recyclerView)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = RecyclerViewAdapter(option?.options ?: emptyList(), this)
    }

    override fun onItemClick(position: Int) {
        val tapOption = option?.options?.elementAtOrNull(position) ?: return
        val guideline = tapOption.guideline
        if (guideline.isNotEmpty()) {
            pushPageStack(GuideFragment.newInstance(guideline))
        } else {
            pushPageStack(newInstance(tapOption))
        }
    }

    data class Option(
        val option: String,
        val symptoms: String,
        val guideline: String,
        val question: String?,
        val options: List<Option> = emptyList()
    )

    companion object {
        fun newInstance(file: String) = DangerLevelFragment().apply {
            val root = readJsonFromAssets(shareMainActivity(), file) ?: JSONObject()
            this.option = parseOption(root)
        }

        fun newInstance(option: Option) = DangerLevelFragment().apply {
            this.option = option
        }

        private fun parseOption(json: JSONObject): Option {
            return Option(
                option = json.optString("option"),
                symptoms = json.optString("symptoms"),
                guideline = json.optString("guideline"),
                question = json.optString("question"),
                options = json.toList("options").map { parseOption(it) }
            )
        }
    }
}


