package vn.nhh.aid.views

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.imageview.ShapeableImageView
import vn.nhh.aid.R
import vn.nhh.aid.utils.standardShapableImageView

class NumberingTextView(context: Context, number: String, content: String) : LinearLayoutCompat(context) {
    val numberText: AppCompatTextView
    val contentText: AppCompatTextView
    init {
        LayoutInflater.from(context).inflate(R.layout.numbering_textview_1_layout, this)
        numberText = findViewById(R.id.number_text)
        contentText = findViewById(R.id.content_text)

        numberText.text = number
        contentText.text = content
    }
}