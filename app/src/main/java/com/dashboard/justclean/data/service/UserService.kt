package com.dashboard.justclean.data.service

import com.dashboard.justclean.data.Model.PostComment
import com.dashboard.justclean.data.Model.PostItem
import com.dashboard.justclean.data.URLFactory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService{

    @GET(URLFactory.Method.post)
    suspend fun getUserList(): Response<List<PostItem>>

    @GET(URLFactory.Method.post+"/{id}/comments")
    suspend fun getPostComment(@Path("id") id:Int): Response<List<PostComment>>

}