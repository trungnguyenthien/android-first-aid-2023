package vn.nhh.aid.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.marginTop
import vn.nhh.aid.R

fun makeMessageToggleButton(context: Context, text: String) = ToggleButton(context).apply {
    textOn = text
    textOff = text
    this.text = text
    isChecked = false
    val newLayoutParam = LinearLayoutCompat.LayoutParams(
        LinearLayoutCompat.LayoutParams.MATCH_PARENT, // width
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT // height
    )
    newLayoutParam.setMargins(0, 0, 0, 10)
    layoutParams = newLayoutParam
    setBackgroundResource(R.drawable.message_toggle_button)
}