package vn.nhh.aid.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import vn.nhh.aid.R

class StepTextComponent(context: Context, attrs: AttributeSet) : LinearLayoutCompat(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_step_text, this)
    }

    fun setData(
        title: String,
        text: String = "",
    ) {
        val titleView: AppCompatTextView = findViewById(R.id.text_title)
        val bodyTextView: AppCompatTextView = findViewById(R.id.text_body)

        titleView.text = title
        bodyTextView.text = text
    }
}