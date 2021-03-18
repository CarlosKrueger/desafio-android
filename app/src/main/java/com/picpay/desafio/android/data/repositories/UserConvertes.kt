package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.data.domain.models.User
import com.picpay.desafio.android.data.database.models.UserEntity
import com.picpay.desafio.android.service.PicPayServiceUser


fun List<UserEntity>.toUsers() = this@toUsers.map { it.toUser() }

fun UserEntity.toUser() = User(
    id = this@toUser.id,
    img = this@toUser.img,
    name = this@toUser.name,
    username = this@toUser.username

)

fun List<PicPayServiceUser>.toUserEntity() = this@toUserEntity.map { it.toUserEntity() }

fun PicPayServiceUser.toUserEntity() = UserEntity(
    id = this@toUserEntity.id!!,
    img = this@toUserEntity.img!!,
    name = this@toUserEntity.name!!,
    username = this@toUserEntity.username!!
)
