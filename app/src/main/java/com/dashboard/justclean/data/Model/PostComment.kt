package com.dashboard.justclean.data.Model

import com.squareup.moshi.Json

data class PostComment(@Json(name = "postId") val userId:Int = 0, @Json(name = "id") val id: Int = 0,
                       @Json(name = "name") val name:String = "", @Json(name = "email") val email:String = "",
                       @Json(name = "body") val body:String = "")