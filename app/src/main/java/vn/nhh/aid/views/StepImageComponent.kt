package vn.nhh.aid.views

import android.content.Context
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
        url: String,
        caption: String = "",
        size: String? =" ",
    ) {
        val imageView: AppCompatImageView = findViewById(R.id.media)
        val captionText: AppCompatTextView = findViewById(R.id.text_caption)
        val l= size!!.split(":")

        captionText.text = caption

        Picasso.get()
            .load(url).centerCrop()
            .placeholder(R.drawable.first_aid)
            .resize(l[0].toInt(), l[1].toInt())
            .into(imageView)

    }
}