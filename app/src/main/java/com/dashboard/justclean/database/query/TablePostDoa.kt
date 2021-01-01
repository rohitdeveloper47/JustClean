package com.dashboard.justclean.database.query

import androidx.room.Dao
import androidx.room.Insert
import com.dashboard.justclean.database.table.TablePost

@Dao
interface TablePostDoa {

    @Insert
    fun post(tablePost: TablePost)

}