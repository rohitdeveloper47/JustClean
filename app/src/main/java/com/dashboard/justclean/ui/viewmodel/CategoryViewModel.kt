package com.dashboard.justclean.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashboard.justclean.data.Model.ResponseBody
import com.dashboard.justclean.data.Model.User
import com.dashboard.justclean.data.repository.UserRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<ResponseBody<List<User>>>()
    val users: LiveData<ResponseBody<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(ResponseBody.loading(null))

                userRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(ResponseBody.success(it.body()))
                    } else _users.postValue(ResponseBody.error(it.errorBody().toString(), null))
                }

        }
    }
}