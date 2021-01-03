package com.dashboard.justclean.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashboard.justclean.data.Model.PostComment
import com.dashboard.justclean.data.Model.PostItem
import com.dashboard.justclean.data.Model.ResponseBody
import com.dashboard.justclean.data.NetworkHelper
import com.dashboard.justclean.data.repository.UserRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val userRepository: UserRepository,private val networkHelper: NetworkHelper) : ViewModel() {
    private val _postItem = MutableLiveData<ResponseBody<List<PostItem>>>()
    private val _commentItem = MutableLiveData<ResponseBody<List<PostComment>>>()

    val postItem: LiveData<ResponseBody<List<PostItem>>>
        get() = _postItem
    val commentItem:LiveData<ResponseBody<List<PostComment>>>
    get() = _commentItem

    init {
        fectchPost()
    }

   private fun fectchPost() {
        viewModelScope.launch {

            if (networkHelper.isNetworkConnected()) {
                userRepository.getUserList().let {
                    if (it.isSuccessful) {
                        _postItem.postValue(ResponseBody.success(it.body()))
                    } else _postItem.postValue(ResponseBody.error(it.errorBody().toString(), null))
                }
            }else{

                _postItem.postValue(ResponseBody.error("No internet connection", null))
            }
        }
    }

    fun addToFavourite(id: Int,userId:Int){



    }

    fun fetchComment(id:Int){
        viewModelScope.launch {

            if (networkHelper.isNetworkConnected()) {
                userRepository.getPostComment(id).let {
                    if (it.isSuccessful) {
                        _commentItem.postValue(ResponseBody.success(it.body()))
                    } else _commentItem.postValue(ResponseBody.error(it.errorBody().toString(), null))
                }

            }else _commentItem.postValue(ResponseBody.error("No internet connection", null))
        }

    }


}