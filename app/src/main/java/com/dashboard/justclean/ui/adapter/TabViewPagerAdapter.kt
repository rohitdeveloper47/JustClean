package com.dashboard.justclean.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dashboard.justclean.R

class TabViewPagerAdapter internal constructor(var context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    var list=ArrayList<Fragment>()
    var titleList=ArrayList<String>()
    override fun getItem(p0: Int)=list.get(p0)

    override fun getCount()=list.size
    fun addFragment(fragment: Fragment, title: String) {
        list.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get(position)
    }

    fun getTabView(position: Int,isSelected:Boolean): View {
        val v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null)
        val tv = v.findViewById(R.id.textViewName) as TextView
        val view = v.findViewById(R.id.view) as TextView
        view.layoutParams.width= tv.measuredWidth / 2
        tv.text = titleList[position]

        return v
    }

}