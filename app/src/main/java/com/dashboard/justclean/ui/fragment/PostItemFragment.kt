package com.dashboard.justclean.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dashboard.justclean.R
import com.dashboard.justclean.exception.ApplicationException
import com.dashboard.justclean.exception.ServerException
import com.dashboard.justclean.ui.adapter.PostAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.net.ConnectException
import java.net.SocketException

class PostItemFragment : Fragment(){
    private var postAdapter : PostAdapter?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_item_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    fun onError(throwable: Throwable, isShowMessage: Boolean) {
        try {

            if (throwable is ServerException) {
                if(isShowMessage) showMessage(throwable.message)
            } else if (throwable is ConnectException || throwable is SocketException) {
                showMessage("Connect to internet")
            } else if (throwable is ApplicationException) {
                showMessage(throwable.message)
            } else{}

        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.e(javaClass.simpleName, "Error From Base framework ", throwable)
    }

    fun showMessage(message: String?) {
        var s: Snackbar?=null
            s= Snackbar.make(this.view!!, message!!, BaseTransientBottomBar.LENGTH_SHORT)
            s!!.view.setBackgroundColor(resources.getColor(R.color.mainColor))
    }

}