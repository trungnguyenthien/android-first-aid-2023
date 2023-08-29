package vn.nhh.aid.views
import android.widget.CompoundButton
import android.widget.ToggleButton

class ToggleButtonGroup {
    private val toggleButtons: MutableList<CompoundButton> = mutableListOf()
    private var onCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null

    fun add(toggleButton: CompoundButton) {
        toggleButtons.add(toggleButton)
        toggleButton.setOnCheckedChangeListener(checkedChangeListener)
    }

    fun allButton() = toggleButtons.toList()

    fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        onCheckedChangeListener = listener
    }

    private val checkedChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if (isChecked) {
            toggleButtons
                .filter { it != buttonView }
                .forEach { it.isChecked = false }
        }
        onCheckedChangeListener?.onCheckedChanged(buttonView, isChecked)
    }
}


/**
 *  questionLayout()?.let {
it.addView(makeMessageToggleButton(requireContext(), "Trong tệp xml của ToggleButton, thêm thuộc tính "))
it.addView(makeMessageToggleButton(requireContext(), "Trong code Kotlin, bạn có thể sử dụng phương thức setTextAlignment() để thiết lập căn chỉnh của văn bản cho ToggleButton. Ví dụ:"))
it.addView(makeMessageToggleButton(requireContext(), "HELLO 3"))
}

val buttons = questionLayout()?.allViews?.mapNotNull { it as? android.widget.ToggleButton }?.toList() ?: emptyList()
ToggleButtonGroup(buttons).setOnCheckedChangeListener { button, isChecked ->

}
 */