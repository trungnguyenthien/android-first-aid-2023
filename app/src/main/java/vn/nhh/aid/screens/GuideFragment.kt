package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import org.json.JSONArray
import org.json.JSONObject
import vn.nhh.aid.R
import vn.nhh.aid.shareMainActivity
import vn.nhh.aid.utils.randUUID
import vn.nhh.aid.utils.readJsonArrayFromAssets
import vn.nhh.aid.utils.toList
import vn.nhh.aid.views.ViewPagerAdapter

private const val GUIDE_ID_PARAM = "GUIDE_ID_PARAM"

class GuideFragment : BaseFragment() {
    private var guideId: String? = null
    private var guide: Guide? = null
    private var steps: List<Step>? = emptyList()
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            guideId = it.getString(GUIDE_ID_PARAM)
            guide = getGuideRoot()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_treatment_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Hướng dẫn sơ cấp cứu"

        val guideTitle: TextView = view.findViewById(R.id.title_tv)
        guideTitle.text = guide?.guideTitle

        viewPager = view.findViewById(R.id.idViewPager)
        steps = guide?.steps ?: return
        viewPagerAdapter = ViewPagerAdapter(requireContext(), steps)
        viewPager.adapter = viewPagerAdapter
    }

    data class Step(
        val id: String,
        val parentId: String? = null,
        val instruction: String? = null,
        val type: String? = null,
        val url: String? = null,
        val size: String? = null,
    )

    data class Guide(
        val id: String,
        val parentId: String? = null,
        val guideId: String? = null,
        val guideTitle: String? = null,
        val steps: List<Step> = emptyList()
    )

    private fun getGuideRoot(): Guide? {
        val root = readJsonArrayFromAssets(shareMainActivity(), "guide.json") ?: return null
        return parseGuide(root)
    }

    private fun parseStep(json: JSONObject, parentId: String = "") = Step(
        id = randUUID(),
        parentId = parentId,
        instruction = json.optString("instruction"),
        size = json.optString("size"),
        url = json.optString("url"),
        type = json.optString("type")
    )

    private fun parseGuide(json: JSONArray, parentId: String = "") = json.toList().firstOrNull {
        it.getString("guideId") == guideId
    }?.let { guideJson ->
        val myId = randUUID()
        Guide(
            id = myId,
            parentId = parentId,
            guideId = guideId,
            guideTitle = guideJson.getString("guideTitle"),
            steps = guideJson.toList("steps").map { stepJson ->
                parseStep(stepJson, parentId = myId)
            },
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(guideId: String?) = GuideFragment().apply {
            arguments = Bundle().apply {
                putString(GUIDE_ID_PARAM, guideId)
            }
        }
    }
}



