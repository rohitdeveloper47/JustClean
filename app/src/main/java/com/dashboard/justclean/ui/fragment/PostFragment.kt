package com.dashboard.justclean.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.dashboard.justclean.R
import com.dashboard.justclean.ui.adapter.TabViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.post_fragment.*

class PostFragment :Fragment(),TabLayout.OnTabSelectedListener{
    lateinit var tabViewPagerAdapter: TabViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.post_fragment,container,false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager()
        tabJustClean.setupWithViewPager(tabViewPager)
        for (i in 0 until tabJustClean.tabCount) {
            val tab = tabJustClean.getTabAt(i)
            tab!!.customView = tabViewPagerAdapter.getTabView(i,false)
        }
        val view = tabJustClean.getTabAt(0)?.customView?.findViewById(R.id.view) as AppCompatTextView
        view.visibility= View.VISIBLE
        tabJustClean.addOnTabSelectedListener(this)

    }

    private fun setupViewPager(){

        tabViewPagerAdapter= TabViewPagerAdapter(this.activity!!,childFragmentManager)
        tabViewPagerAdapter.addFragment(PostItemFragment(),getString(R.string.tab_item_1))
        tabViewPagerAdapter.addFragment(MyFavouriteFragment(),getString(R.string.tab_item_2))
        tabViewPager.offscreenPageLimit=0
        tabViewPager.adapter=tabViewPagerAdapter

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {


    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        val view = tab?.customView?.findViewById(R.id.view) as AppCompatTextView
        view.visibility=View.INVISIBLE
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        
        tabViewPager.currentItem = tab!!.position
        val view = tab.customView?.findViewById(R.id.view) as AppCompatTextView
        view.visibility=View.VISIBLE

    }



}