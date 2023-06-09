package vn.nhh.aid.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.ToggleButton

fun makeMessageToggleButton(context: Context, text: String): ToggleButton {
    val view = ToggleButton(context)
    val layoutParams = view.layoutParams
    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
    view.textOn = text
    view.textOff = text
    return view
}