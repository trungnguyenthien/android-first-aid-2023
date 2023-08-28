package vn.nhh.aid.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewpager.widget.PagerAdapter
import vn.nhh.aid.screens.GuideFragment
import vn.nhh.aid.shareMainActivity

class ViewPagerAdapter(context: Context, val Guidelist: List<GuideFragment.Step>?) : PagerAdapter(){

    override fun getCount(): Int {
        return Guidelist?.size!!
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayoutCompat
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val caption = Guidelist?.get(position)?.instruction
    val url = Guidelist?.get(position)?.url

    val imageTextView = StepImageComponent(shareMainActivity()).apply {
        if (caption != null && url != null)
            setData(url,caption)
        }
    container.addView(imageTextView)
    return imageTextView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayoutCompat)
    }

}
