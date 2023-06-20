package vn.nhh.aid.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import vn.nhh.aid.R
import vn.nhh.aid.utils.parseVideoId

class StepVideoComponent(context: Context) : LinearLayoutCompat(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_step_video, this)
    }

    fun setData(
        title: String,
        url: String,
        caption: String = "",
        text: String = "",
    ) {
        val titleView: AppCompatTextView = findViewById(R.id.text_title)
        val webView: WebView = findViewById(R.id.youtubeWebView)
        val captionText: AppCompatTextView = findViewById(R.id.text_caption)
        val bodyTextView: AppCompatTextView = findViewById(R.id.text_body)

        titleView.text = title
        captionText.text = caption
        bodyTextView.text = text

        webView.settings.javaScriptEnabled = true

        val videoId = parseVideoId(youtubeUrl = url)
        val html = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$videoId\" frameborder=\"0\" allowfullscreen></iframe>"
        webView.loadData(html, "text/html", "utf-8")
    }
}