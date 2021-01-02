package com.dashboard.justclean.data.datasource

import com.dashboard.justclean.data.Model.PostComment
import com.dashboard.justclean.data.Model.PostItem
import retrofit2.Response

interface DataSource {

    suspend fun getUserList(): Response<List<PostItem>>

    suspend fun getPostComment(id:Int): Response<List<PostComment>>
}