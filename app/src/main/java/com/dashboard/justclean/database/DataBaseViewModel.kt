package com.dashboard.justclean.database

import com.dashboard.justclean.JustClean

class DataBaseViewModel{

    private val justCleanDatabase = JustCleanDatabase.invoke(JustClean.instance)
    private val tablePost = justCleanDatabase.tablePost()
    private val tableFavourite = justCleanDatabase.tableFavourite()

}