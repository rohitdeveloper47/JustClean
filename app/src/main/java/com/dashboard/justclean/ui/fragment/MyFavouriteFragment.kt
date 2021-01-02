package com.dashboard.justclean.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dashboard.justclean.R
import com.dashboard.justclean.database.DataBaseViewModel
import com.dashboard.justclean.database.table.TableFavourite
import com.dashboard.justclean.ui.adapter.FavouritAdapter
import kotlinx.android.synthetic.main.my_favourite_fragment.*

class MyFavouriteFragment : Fragment(){

    private var dataBaseViewModel = DataBaseViewModel()
    private var favouriteList : List<TableFavourite>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_favourite_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpRecylerView()

    }

    private fun setUpRecylerView(){

        recyclerFavouriteList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerFavouriteList.adapter = FavouritAdapter(getList()!!, object : FavouritAdapter.RecycleItemClick {
            override fun onItemClick(item: TableFavourite) {

            }
        })
    }

    private fun getList(): List<TableFavourite>? {

        favouriteList = dataBaseViewModel.getFavouriteList()

        return favouriteList
    }
}