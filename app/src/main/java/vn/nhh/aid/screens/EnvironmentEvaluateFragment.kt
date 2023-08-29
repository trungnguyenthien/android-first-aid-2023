package vn.nhh.aid.screens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import net.cachapa.expandablelayout.ExpandableLayout
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack
import vn.nhh.aid.utils.LinearLayoutGravity
import vn.nhh.aid.utils.getListString
import vn.nhh.aid.utils.makeButtonTextColorStateList
import vn.nhh.aid.utils.makeLinearLayoutParam
import vn.nhh.aid.utils.readJsonFromAssets
import vn.nhh.aid.views.NumberingTextView


class EnvironmentEvaluateFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enviroment_evaluation, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val exLayoutContent = view.findViewById<LinearLayoutCompat>(R.id.expandable_layout_content)
        val data = readData()
        data.list.forEachIndexed { index, text ->
            exLayoutContent.addView(NumberingTextView(requireContext(), "$index", text))
        }

        val exLayout = view.findViewById<ExpandableLayout>(R.id.expandable_layout)
        exLayout.collapse(false)

        view.findViewById<LinearLayoutCompat>(R.id.ex_button_layout).setOnClickListener {
            exLayout.toggle(true)
        }

        val exLayoutContent2 = view.findViewById<LinearLayoutCompat>(R.id.expandable_layout_content_2)
        exLayoutContent2.addView(makeButton("Biết rõ loại chấn thương").apply {
            setOnClickListener {
                pushPageStack(DangerLevelJudgeFragment.newInstance())
            }
        })

        exLayoutContent2.addView(makeButton("Cần thêm dấu hiệu phán đoán").apply {
            setOnClickListener {
                pushPageStack(JudgeFragment.newInstance())
            }
        })

        val exLayout2 = view.findViewById<ExpandableLayout>(R.id.expandable_layout_2)
        exLayout2.expand()

        view.findViewById<LinearLayoutCompat>(R.id.ex_button_layout_2).setOnClickListener {
            exLayout2.toggle(true)
        }
    }

    private fun makeButton(title: String) = AppCompatButton(requireContext()).apply {
        this.text = title
        isAllCaps = false
        setBackgroundResource(R.color.CLEAR)
        setTextColor(makeButtonTextColorStateList(normal = Color.GRAY, pressed = Color.LTGRAY))
        layoutParams = makeLinearLayoutParam(
            ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            ),
            gravity = LinearLayoutGravity.GRAVITY_LEFT
        )
    }

    data class SoCapCuuData(
        var list: List<String> = listOf(),
        var important: List<String> = listOf()
    )
    private fun readData(): SoCapCuuData {
        val output = SoCapCuuData()
        val root = readJsonFromAssets(requireContext(), "so_cuu_co_ban.json") ?: return output
        output.list = root.getListString("list")
        output.important = root.getListString("important")
        return output
    }

    companion object {
        @JvmStatic
        fun newInstance() = EnvironmentEvaluateFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}
