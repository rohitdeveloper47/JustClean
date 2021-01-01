package com.dashboard.justclean.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_post")
class TablePost(@PrimaryKey(autoGenerate = true) var id: Int = 0){



}