package com.dashboard.justclean.database.query

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.dashboard.justclean.database.table.TableFavourite

@Dao
interface TableFavouriteDoa{

    @Insert
    fun favourite(tableFavourite: TableFavourite)

    @Transaction
    @Query("SELECT * FROM tbl_favourite")
    fun getFavouriteList():List<TableFavourite>

    @Transaction
    @Query("SELECT * FROM tbl_favourite WHERE update_to_server = 1")
    fun getPendingFavouriteList():List<TableFavourite>
}