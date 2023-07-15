package vn.nhh.aid.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import vn.nhh.aid.screens.GuideFragment
import vn.nhh.aid.shareMainActivity
import vn.nhh.aid.utils.shareContext
import java.util.Objects

class ViewPagerAdapter(context: Context, val Guidelist: List<GuideFragment.Step>?) : PagerAdapter(){


    override fun getCount(): Int {
        return Guidelist?.size!!
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val caption = Guidelist?.get(position)?.Instruction
    val url = Guidelist?.get(position)?.url

    val imageTextView = StepTextComponent(shareMainActivity()).apply {
        if (caption != null && url != null)
            setData(caption, url)
        }
    Objects.requireNonNull(container).addView(imageTextView)
    return imageTextView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}
