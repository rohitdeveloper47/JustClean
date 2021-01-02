package com.dashboard.justclean.ui.fragment

import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.WorkManager
import com.dashboard.justclean.JustClean
import com.dashboard.justclean.R
import com.dashboard.justclean.data.Model.PostComment
import com.dashboard.justclean.data.Model.ResponseBody
import com.dashboard.justclean.database.DataBaseViewModel
import com.dashboard.justclean.ui.adapter.PostCommentAdapter
import com.dashboard.justclean.ui.viewmodel.CategoryViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.post_comment_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class PostCommentFragment : Fragment(){
    private val categoryViewModel : CategoryViewModel by viewModel()
    lateinit var checkBoxFavourite: CheckBox
    private var id:Int?=null
    private var userId:Int?=null
    private var title:String?=null
    private var body:String?=null
    private var dataBaseViewModel = DataBaseViewModel()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        if(arguments!!.containsKey("ID")) id =  arguments?.getInt("ID")
        if(arguments!!.containsKey("USER_ID")) userId =  arguments?.getInt("USER_ID")
        if(arguments!!.containsKey("TITLE")) title =  arguments?.getString("TITLE")
        if(arguments!!.containsKey("BODY"))body =  arguments?.getString("BODY")

        id?.let {
            categoryViewModel.fetchComment(id!!)
            observeComment()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.post_comment_fragment,container,false)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val favorite = layoutInflater.inflate(R.layout.raw_toolbar_image, null)
        checkBoxFavourite = favorite.findViewById<CheckBox>(R.id.checkBox)
        val outValue = TypedValue()
        context!!.theme.resolveAttribute(R.attr.selectableItemBackgroundBorderless, outValue, true)
        menu.add(0, 0, 0, "").setActionView(checkBoxFavourite).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        checkBoxFavourite.setOnClickListener {

            //Add post to favourite
            categoryViewModel.addToFavourite(id!!,userId!!)
            observeFavourite()

        }

    }

    private fun observeComment(){

        categoryViewModel.commentItem.observe(this, Observer {
            when (it.status) {
                ResponseBody.Status.SUCCESS -> {

                    it.data?.let { list -> setUpRecyclerView(list) }

                }

                ResponseBody.Status.ERROR -> {
                    //Handle Error
                    showMessage(it.message)
                }
            }
        })

    }

    fun showMessage(message: String?) {
        var s: Snackbar?=null
        s= Snackbar.make(this.requireView(), message!!, BaseTransientBottomBar.LENGTH_SHORT)
        s!!.view.setBackgroundColor(resources.getColor(R.color.mainColor))
    }

    private fun observeFavourite(){

        //if response is success set update item to 0 else 1. ByDefault set to 1. Currently we are not using api to send favourite post by userId on server.
        // So assume here we recevied network issue on device and value inserted to database.

        dataBaseViewModel.addToFavourite(id!!,userId!!,title!!,body!!)

        //Now Creating a task to sync data when internet is not available.
        //This task will sync periodically and check weather internet is available or not.
        // if device recevied internet connectivity this task will sync list of all favourite and send all value to server

        //Calling this function inside onError method
        WorkManager.getInstance().enqueue(JustClean.oneTimeWork);
        observeTaskManager()

    }

    private fun setUpRecyclerView(list: List<PostComment>){

        recyclerViewComment.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerViewComment.adapter = PostCommentAdapter(list, object : PostCommentAdapter.RecycleItemClick {
            override fun onItemClick(view: View) {


            }
        })

    }

    private fun observeTaskManager(){

        WorkManager.getInstance().getWorkInfoByIdLiveData(JustClean.oneTimeWork.id)
            .observe(this, Observer { workInfo -> //Displaying the status into TextView
                val status = workInfo.state.name
                if(status.equals("SUCCEEDED")){

                    //fList = dataBaseViewModel.getPendingFavouriteList()
                    //categoryViewModel.sendPendingFavoriteList(fList)
                    //ObserveFavouritePendingList()

                    //Cancelled current task by ID
                    WorkManager.getInstance().cancelWorkById(JustClean.oneTimeWork.id)

                }else{

                    WorkManager.getInstance().enqueue(JustClean.oneTimeWork);

                }

            })
    }
}