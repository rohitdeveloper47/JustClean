package com.dashboard.justclean.data.repository

import com.dashboard.justclean.data.datasource.DataSource

class UserRepository (private val dataSource: DataSource) {

    suspend fun getUsers() =  dataSource.getUsers()

}