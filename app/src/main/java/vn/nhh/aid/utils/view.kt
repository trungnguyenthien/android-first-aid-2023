package vn.nhh.aid.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.setPadding
import androidx.core.widget.TextViewCompat
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.squareup.picasso.Picasso
import vn.nhh.aid.R
import vn.nhh.aid.shareMainActivity
import java.util.regex.Pattern

//AIzaSyDiL3mPyOIdtvSxChQkilRcVhi58fUQi1o
fun shareContext(): Context = shareMainActivity()
fun Int.dp(): Int = (this * shareContext().resources.displayMetrics.density).toInt()
fun Int.sp() = (this * shareContext().resources.displayMetrics.scaledDensity).toInt()

fun standardShapableImageView(imageView: ShapeableImageView) {
    val shapeAppearanceModel = imageView.shapeAppearanceModel
        .toBuilder()
        .setAllCorners(CornerFamily.ROUNDED, 12.dp().toFloat())
        .build()

    imageView.shapeAppearanceModel = shapeAppearanceModel
}

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
fun makeButtonTextColorStateList(@ColorInt normal: Int, @ColorInt pressed: Int) = ColorStateList(
    arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf()),
    intArrayOf(pressed, normal)
)

fun makeTextColorStateList(@ColorInt normal: Int, @ColorInt pressed: Int) = ColorStateList(
    arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf()),
    intArrayOf(pressed, normal)
)

enum class LinearLayoutGravity(val value: Int) {
    GRAVITY_TOP(0x30),
    GRAVITY_BOTTOM(0x50),
    GRAVITY_LEFT(0x03),
    GRAVITY_RIGHT(0x05),
    GRAVITY_CENTER_VERTICAL(0x10),
    GRAVITY_FILL_VERTICAL(0x70),
    GRAVITY_CENTER_HORIZONTAL(0x01),
    GRAVITY_FILL_HORIZONTAL(0x07),
    GRAVITY_CENTER(0x11),
    GRAVITY_FILL(0x77)
}
fun makeLinearLayoutParam(
    marginParams: MarginLayoutParams,
    gravity: LinearLayoutGravity
) = LinearLayoutCompat.LayoutParams(marginParams).apply {
    this.gravity = gravity.value
}

fun loadImageByUrl(url: String, imageView: ImageView) {
    Picasso.get().load(url).into(imageView)
}

fun makeTitleTextView(title: String) = TextView(shareMainActivity()).apply {
    text = title
    textSize = 30F
    setTextColor(makeTextColorStateList(normal = Color.BLACK, pressed = Color.GRAY))
}