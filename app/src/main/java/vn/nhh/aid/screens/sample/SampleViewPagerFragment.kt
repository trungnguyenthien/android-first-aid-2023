package vn.nhh.aid.screens.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.PageTransformer
import vn.nhh.aid.R
import vn.nhh.aid.screens.BaseFragment
import vn.nhh.aid.screens.BaseListFragment
import vn.nhh.aid.utils.makeMessageText


/**
 * A simple [Fragment] subclass.
 * Use the [SampleViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SampleViewPagerFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sample_view_pager, container, false)
    }
    var viewPager: ViewPager2? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createListPage()

        viewPager = view.findViewById(R.id.pager)
        viewPager?.adapter = PagerAdapter(this)
        viewPager?.setPageTransformer(SideBySideTransformer());
    }

    private fun createListPage() {
        fragments.clear()
        /// Page 1
        val page1 = BaseListFragment()
        page1.onViewCreatedFunc = {
            page1.headerLinearLayout.addView(
                makeMessageText(requireContext(), "FRAGMENT 1")
            )
        }
        fragments.add(page1)

        /// Page 2
        val page2 = BaseListFragment()
        page2.onViewCreatedFunc = {
            page2.headerLinearLayout.addView(
                makeMessageText(requireContext(), "FRAGMENT 2")
            )
        }
        fragments.add(page2)

        /// Page 3
        val page3 = BaseListFragment()
        page3.onViewCreatedFunc = {
            page3.headerLinearLayout.addView(
                makeMessageText(requireContext(), "FRAGMENT 3")
            )
        }
        fragments.add(page3)
    }

    inner class ViewFragment(private val root: View): Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ) = root
    }

    var fragments = mutableListOf<Fragment>()
    inner class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
        override fun getItemCount() = fragments.size
        override fun createFragment(position: Int) = fragments[position]
    }
}

class SideBySideTransformer @JvmOverloads  constructor(var minScale: Float = 0.85f) : PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width.toFloat()
        val pageHeight = page.height.toFloat()
        page.pivotX = pageWidth / 2f
        page.pivotY = pageHeight / 2f
        if (position < -1) {
            page.pivotX = pageWidth
            page.scaleX = minScale
            page.scaleY = minScale
        } else if (position <= 1) {
            val scaleFactor = Math.max(minScale, 1 - Math.abs(position))
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor
            page.pivotX = pageWidth / 2f * (1 - position)
        } else {
            page.pivotX = 0f
            page.scaleX = minScale
            page.scaleY = minScale
        }
    }
}