package vn.nhh.aid.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.TextViewCompat
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack
import vn.nhh.aid.utils.LinearLayoutGravity
import vn.nhh.aid.utils.dp
import vn.nhh.aid.utils.makeButtonTextColorStateList
import vn.nhh.aid.utils.makeLinearLayoutParam
import vn.nhh.aid.utils.makeTitleTextView
import vn.nhh.aid.utils.readJsonFromAssets
import vn.nhh.aid.utils.shareContext
import vn.nhh.aid.utils.toList

class JudgeFragment: BaseListFragment() {
    companion object {
        @JvmStatic
        fun newInstance() =  JudgeFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = readData()

        headerLinearLayout.addView(makeTitleTextView(data.title))
        footerLinearLayout.addView(makeListItemButton().apply {
            text = data.otherText
            setOnClickListener {
                pushPageStack(DangerLevelFragment.newInstance(file = "dau_hieu_khac.json"))
            }
        })
        data.signs.forEach {
            val buttonText = it.text
            val guideId = it.guideId
            bodyLinearLayout.addView(makeListItemButton().apply {
                text = buttonText
                setOnClickListener {
                    pushPageStack(GuideFragment.newInstance(guideId = guideId))
                }
            })
        }
    }

    data class Sign(
        var text: String = "",
        var guideId: String = "",
    )
    data class Data(
        var title: String = "",
        var signs: List<Sign> = emptyList(),
        var otherText: String = ""
    )
    private fun readData(): Data {
        val output = Data()
        val rootJson = readJsonFromAssets(requireContext(), "dau_hieu_dien_hinh.json") ?: return output
        output.title = rootJson.optString("title") ?: ""
        output.signs = rootJson.toList("dau_hieu").map {
            val text = it.optString("text", "")
            val guideId = it.optString("guide_id", "")
            return@map Sign(text, guideId)
        }
        output.otherText = rootJson.optString("other_text_button") ?: ""
        return output
    }
}

fun makeListItemButton() = AppCompatButton(shareContext()).apply {
    isAllCaps = false
    setBackgroundResource(R.drawable.blue_rounded_button)
    setTextColor(makeButtonTextColorStateList(normal = Color.WHITE, pressed = Color.LTGRAY))
    layoutParams = makeLinearLayoutParam(
        ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80.dp()).apply {
            this.topMargin = 10.dp()
        },
        gravity = LinearLayoutGravity.GRAVITY_CENTER
    )
}