package com.dashboard.justclean.data.datasource

import com.dashboard.justclean.data.Model.PostComment
import com.dashboard.justclean.data.Model.PostItem
import com.dashboard.justclean.data.service.UserService
import retrofit2.Response

class DataSourceImp(private val dataSource: UserService) : DataSource {

    override suspend fun getUserList(): Response<List<PostItem>> = dataSource.getUserList()
    override suspend fun getPostComment(id:Int): Response<List<PostComment>>  = dataSource.getPostComment(id)

}