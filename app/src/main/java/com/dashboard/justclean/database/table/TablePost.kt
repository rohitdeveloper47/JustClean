package com.dashboard.justclean.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_post")
class TablePost(@PrimaryKey(autoGenerate = true) var id: Int = 0,@ColumnInfo(name = "userId") var userId:Int,@ColumnInfo(name = "post_id") var post_id:Int,@ColumnInfo(name = "title") var title:String,@ColumnInfo(name = "body") var body:String){
}