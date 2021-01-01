package com.dashboard.justclean.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dashboard.justclean.database.query.TableFavouriteDoa
import com.dashboard.justclean.database.query.TablePostDoa
import com.dashboard.justclean.database.table.TableFavourite
import com.dashboard.justclean.database.table.TablePost

@Database(entities = [TablePost::class,TableFavourite::class],version = 1,exportSchema = false)
public abstract class JustCleanDatabase : RoomDatabase(){
    abstract fun tablePost() : TablePostDoa
    abstract fun tableFavourite() : TableFavouriteDoa
    companion object{
        @Volatile private var instance: JustCleanDatabase?=null
        private val SHUT = Any()
        operator fun invoke(context: Context)= instance ?: synchronized(SHUT){ instance ?: buildDatabase(context).also { instance = it} }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context, JustCleanDatabase::class.java, "justClean.db").allowMainThreadQueries().build()

    }


}