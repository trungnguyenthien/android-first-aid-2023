package vn.nhh.aid.views

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewpager.widget.PagerAdapter
import vn.nhh.aid.screens.GuideFragment
import vn.nhh.aid.shareMainActivity

class StepPagerAdapter(private val steps: List<GuideFragment.Step>?) : PagerAdapter(){

    override fun getCount() = steps?.size ?: 0

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayoutCompat
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val caption = steps?.get(position)?.instruction
    val url = steps?.get(position)?.url

    val imageTextView = StepImageComponent(shareMainActivity()).apply {
        if (caption != null && url != null)
            setData(caption)
        }
    container.addView(imageTextView)
    return imageTextView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayoutCompat)
    }
}
