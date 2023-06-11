package vn.nhh.aid.utils
import android.widget.CompoundButton
import android.widget.ToggleButton

class ToggleButtonGroup(buttons: List<ToggleButton> = emptyList()) {
    private val toggleButtons: MutableList<CompoundButton> = mutableListOf()
    private var onCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null

    init {
        buttons.forEach { add(it) }
    }

    fun add(toggleButton: CompoundButton) {
        toggleButtons.add(toggleButton)
        toggleButton.setOnCheckedChangeListener(checkedChangeListener)
    }

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
