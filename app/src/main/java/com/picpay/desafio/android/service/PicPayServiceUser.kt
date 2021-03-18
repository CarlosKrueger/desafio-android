package com.picpay.desafio.android.service

import com.google.gson.annotations.SerializedName

data class PicPayServiceUser(
    @SerializedName("img")
    val img: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("username")
    val username: String? = null
)
