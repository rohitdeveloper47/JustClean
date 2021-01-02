package com.dashboard.justclean.database

import com.dashboard.justclean.JustClean
import com.dashboard.justclean.data.Model.PostItem
import com.dashboard.justclean.database.table.TableFavourite
import com.dashboard.justclean.database.table.TablePost

class DataBaseViewModel(){
    private val justCleanDatabase = JustCleanDatabase.invoke(JustClean.instance)
    private var tablePost = justCleanDatabase.tablePost()
    private val tableFavourite = justCleanDatabase.tableFavourite()

    fun insertData(list:List<PostItem>) {

        list.forEach {

            tablePost.post(TablePost(post_id = it.id,userId = it.userId,title = it.title,body = it.body))

        }

    }

    fun getData() : List<TablePost> {

       return tablePost.getList()

    }

    fun getPendingFavouriteList():List<TableFavourite>{

        return tableFavourite.getFavouriteList()
    }

    fun addToFavourite(id:Int,userId:Int,title:String,body:String){

        tableFavourite.favourite(TableFavourite(post_id = id,userId = userId,title = title,body = body,update = 1))
    }
    fun getFavouriteList(): List<TableFavourite> {

        return tableFavourite.getFavouriteList()

    }


}