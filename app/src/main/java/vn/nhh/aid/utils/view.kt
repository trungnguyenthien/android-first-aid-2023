package vn.nhh.aid.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.ToggleButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.setPadding
import com.squareup.picasso.Picasso
import vn.nhh.aid.R
import vn.nhh.aid.screens.BaseListFragment
import vn.nhh.aid.screens.BaseListFragmentController
import vn.nhh.aid.screens.BaseListFragmentView
import vn.nhh.aid.shareMainActivity
import java.util.regex.Pattern

//AIzaSyDiL3mPyOIdtvSxChQkilRcVhi58fUQi1o
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

fun parseVideoId(youtubeUrl: String): String? {
    val regex = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v=|\\/videos\\/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(youtubeUrl)
    return if (matcher.find()) {
        matcher.group()
    } else {
        null
    }
}

fun loadImageByUrl(url: String, imageView: ImageView) {
    Picasso.get().load(url).into(imageView)
}

typealias HbfFragmentHandleFunc = (view: BaseListFragmentView) -> Unit
fun makeHbfFragment(
    resume: HbfFragmentHandleFunc,
    create: HbfFragmentHandleFunc
) = BaseListFragment(object: BaseListFragmentController {
    override fun onViewCreated(view: BaseListFragmentView) {
        create.invoke(view)
    }

    override fun onResume(view: BaseListFragmentView) {
        resume.invoke(view)
    }

    override fun onPressBack(view: BaseListFragmentView) { }
})