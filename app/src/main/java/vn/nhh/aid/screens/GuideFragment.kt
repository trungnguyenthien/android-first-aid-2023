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
    private var guideObject: Guide? = null
    private var Lstep: List<Step>? = emptyList()
    var viewPager: ViewPager? = null
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            guideId = it.getString(GUIDE_ID_PARAM)
            guideObject = getGuideRoot()
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
        requireActivity().title = "Câu hỏi đánh giá"

        val text = guideObject?.Proname
        val TxtView:TextView = view.findViewById(R.id.title_tv)
        TxtView.text = text
        Lstep = guideObject?.Steps
        viewPagerAdapter = ViewPagerAdapter(requireContext(), Lstep)
        viewPager?.adapter = viewPagerAdapter

    }


    data class Step(
        val id: String,
        val parentId: String? = null,
        val Instruction: String? = null,
        val type: String? = null,
        val url: String? = null,
        val size: String? = null,
    )
    data class Guide(
        val id: String,
        val parentId: String? = null,
        val guideline: String? = null,
        val Proname: String? = null,
        val Steps: List<Step> = emptyList()
    )

    fun getGuideRoot(): Guide? {
        val root = readJsonArrayFromAssets(shareMainActivity(), "guide.json") ?: return null
        return parseGuide(root)
    }

    private fun parseStep(json: JSONObject, parentId: String =""): Step {
        val myId = randUUID()
        return Step(
            id = myId,
            parentId = parentId,
            Instruction = json.optString("Instruction"),
            size = json.optString("size"),
            url = json.optString("gu"),
            type =  json.optString("type")
        )
    }

    private fun parseGuide(json: JSONArray, parentId: String = ""): Guide? {
        for (i in 0 until json.length())
        {
            val temp1 = json.getJSONObject(i).getString("guideId")
            if(temp1 == guideId)
            {
                val myId = randUUID()
                return Guide(
                    id = myId,
                    parentId = parentId,
                    Proname = json.optJSONObject(i).getString("ProName"),
                    guideline = json.optJSONObject(i).getString("guideId"),
                    Steps =  json.optJSONObject(i).toList("Steps").map{parseStep(it, parentId = myId)}
                )

            }
        }
        return null
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



