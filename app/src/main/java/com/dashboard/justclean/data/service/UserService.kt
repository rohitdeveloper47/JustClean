package com.dashboard.justclean.data.service

import com.dashboard.justclean.data.Model.User
import retrofit2.Response
import retrofit2.http.PUT

interface UserService{

    @PUT("posts")
    suspend fun getUsers(): Response<List<User>>


}