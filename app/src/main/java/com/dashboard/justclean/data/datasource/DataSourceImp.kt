package com.dashboard.justclean.data.datasource

import com.dashboard.justclean.data.Model.User
import retrofit2.Response

class DataSourceImp(private val dataSource: DataSource) : DataSource {

    override suspend fun getUsers(): Response<List<User>> = dataSource.getUsers()

}