package com.dashboard.justclean.data.Model

import com.squareup.moshi.Json

 data class PostItem(@Json(name = "userId") var userId:Int = 0, @Json(name = "id") var id: Int = 0, @Json(name = "title") var title:String = "", @Json(name = "body") var body:String = ""
 )
