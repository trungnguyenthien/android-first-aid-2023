package vn.nhh.aid.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.widget.ImageViewCompat
import com.squareup.picasso.Picasso
import vn.nhh.aid.R
import vn.nhh.aid.utils.loadImageByUrl

class ImageTextComponent(context: Context, attrs: AttributeSet) : LinearLayoutCompat(context, attrs) {
    val imageView: AppCompatImageView
    val textView: AppCompatTextView
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_image_text, this)
        imageView = findViewById(R.id.image)
        textView = findViewById(R.id.text)
    }

    fun setImageUrl(url: String) {
        loadImageByUrl(url, imageView)
    }

    fun setImageId(@DrawableRes resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setText(text: String) {
        textView.text = text
    }
}