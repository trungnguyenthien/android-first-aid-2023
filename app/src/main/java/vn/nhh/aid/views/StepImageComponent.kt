package vn.nhh.aid.views

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import vn.nhh.aid.R

class StepImageComponent(context: Context) : LinearLayoutCompat(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_step_image, this)
    }

    fun setData(
        caption: String = "",
    ) {
        //val imageView: AppCompatImageView = findViewById(R.id.media)
        val captionText: AppCompatTextView = findViewById(R.id.text_caption)

        captionText.text = caption

        //Picasso.get()
            //.load(url)
            //.into(imageView)
    }
}