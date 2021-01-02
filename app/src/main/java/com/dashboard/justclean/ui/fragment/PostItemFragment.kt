package com.dashboard.justclean.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dashboard.justclean.R
import com.dashboard.justclean.data.Model.PostItem
import com.dashboard.justclean.data.Model.ResponseBody
import com.dashboard.justclean.database.DataBaseViewModel
import com.dashboard.justclean.database.table.TablePost
import com.dashboard.justclean.ui.adapter.PostAdapter
import com.dashboard.justclean.ui.viewmodel.CategoryViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.post_item_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostItemFragment : Fragment(){
    private val categoryViewModel : CategoryViewModel by viewModel()
    private var databaseModel = DataBaseViewModel()
    private var mList:List<TablePost>?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_item_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeList()

    }

    private fun observeList(){

        categoryViewModel.postItem.observe(this, Observer {
            when (it.status) {
                ResponseBody.Status.SUCCESS -> {

                    it.data?.let { list ->

                        setUpRecyclerView(list)
                        // databaseModel.insertData(list)
                    }


                }

                ResponseBody.Status.ERROR -> {
                    //Handle Error
                    Log.d("IsNetwork","${it.message}")
                    showMessage(it.message)
                }
            }
        })

    }

    fun showMessage(message: String?) {
        var s: Snackbar?=null
            s= Snackbar.make(this.view!!, message!!, BaseTransientBottomBar.LENGTH_LONG)
            s!!.view.setBackgroundColor(resources.getColor(R.color.mainColor))
            s.show()
    }

    private fun setUpRecyclerView(list:List<PostItem>){

        recyclerPostList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerPostList.adapter = PostAdapter(list, object : PostAdapter.RecycleItemClick {
            override fun onItemClick(item: PostItem) {

                val fragment: Fragment = PostCommentFragment()
                val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                val ft: FragmentTransaction =
                    fragmentManager.beginTransaction()
                ft.replace(R.id.post_fragment, fragment)
                ft.addToBackStack("tag")
                val b = Bundle()
                b.putInt("ID",item.id)
                b.putInt("USER_ID",item.userId)
                b.putString("TITLE",item.title)
                b.putString("BODY",item.body)
                fragment.arguments = b
                ft.commit()

            }
        })
    }

}