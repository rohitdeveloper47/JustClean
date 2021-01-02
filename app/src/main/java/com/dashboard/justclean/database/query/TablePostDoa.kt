package com.dashboard.justclean.database.query

import androidx.room.*
import com.dashboard.justclean.database.table.TablePost

@Dao
interface TablePostDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun post(tablePost: TablePost)

    @Transaction
    @Query("SELECT * FROM tbl_post")
    fun getList():List<TablePost>

}