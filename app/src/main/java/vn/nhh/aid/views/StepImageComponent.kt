package vn.nhh.aid.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.squareup.picasso.Picasso
import vn.nhh.aid.R

class StepImageComponent(context: Context) : LinearLayoutCompat(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_step_image, this)
    }

    fun setData(
        title: String,
        url: String,
        caption: String = "",
        text: String = "",
    ) {
        val titleView: AppCompatTextView = findViewById(R.id.text_title)
        val imageView: AppCompatImageView = findViewById(R.id.media)
        val captionText: AppCompatTextView = findViewById(R.id.text_caption)
        val bodyTextView: AppCompatTextView = findViewById(R.id.text_body)

        titleView.text = title
        captionText.text = caption
        bodyTextView.text = text

        Picasso.get()
            .load(url)
            .into(imageView)
    }
}