package com.dashboard.justclean.data.repository

import com.dashboard.justclean.data.datasource.DataSource

class UserRepository (private val dataSource: DataSource) {

    suspend fun getUserList() =  dataSource.getUserList()

    suspend fun getPostComment(id:Int) =  dataSource.getPostComment(id)

}