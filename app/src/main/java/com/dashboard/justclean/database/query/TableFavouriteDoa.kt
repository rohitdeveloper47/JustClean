package com.dashboard.justclean.database.query

import androidx.room.Dao
import androidx.room.Insert
import com.dashboard.justclean.database.table.TableFavourite

@Dao
interface TableFavouriteDoa{

    @Insert
    fun favourite(tableFavourite: TableFavourite)
}