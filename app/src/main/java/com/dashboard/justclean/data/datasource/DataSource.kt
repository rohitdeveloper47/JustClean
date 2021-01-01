package com.dashboard.justclean.data.datasource

import com.dashboard.justclean.data.Model.User
import retrofit2.Response

interface DataSource {

    suspend fun getUsers(): Response<List<User>>
}