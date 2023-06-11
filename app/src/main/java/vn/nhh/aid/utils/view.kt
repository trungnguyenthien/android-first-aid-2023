package vn.nhh.aid.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.ToggleButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.setPadding
import vn.nhh.aid.R
import vn.nhh.aid.shareMainActivity

fun shareContext(): Context = shareMainActivity()
fun Int.dp(): Int = (this * shareContext().resources.displayMetrics.density).toInt()
fun Int.sp() = (this * shareContext().resources.displayMetrics.scaledDensity).toInt()

fun makeMessageToggleButton(context: Context, text: String) = ToggleButton(context).apply {
    textOn = text
    textOff = text
    this.text = text
    isChecked = false
    val newLayoutParam = LinearLayoutCompat.LayoutParams(
        LinearLayoutCompat.LayoutParams.MATCH_PARENT, // width
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT // height
    )
    newLayoutParam.setMargins(0, 0, 0, 10.dp())
    layoutParams = newLayoutParam
    setBackgroundResource(R.drawable.message_toggle_button)
    val colorStateList = ColorStateList(
        arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        ),
        intArrayOf(
            Color.parseColor("#000000"),
            Color.parseColor("#000000")
        )
    )
    setTextColor(colorStateList)
    textAlignment = View.TEXT_ALIGNMENT_TEXT_START
    setPadding(10.dp())
    typeface = Typeface.MONOSPACE
}

fun makeRadioGroup(context: Context) = RadioGroup(context).apply {
    layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}