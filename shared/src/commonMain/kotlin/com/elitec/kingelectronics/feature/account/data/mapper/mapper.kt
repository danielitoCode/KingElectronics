package com.elitec.kingelectronics.feature.account.data.mapper

import com.elitec.kingelectronics.feature.account.data.dto.UserDto
import com.elitec.kingelectronics.feature.account.domain.entity.User

fun User.toDto(): UserDto =
    UserDto(
        id = this.id,
        name = this.name,
        email = this.email,
        pass = this.pass,
        sub = this.sub,
        phone = this.phone,
        photoUrl = this.photoUrl,
        verification = this.verification
    )

fun UserDto.toDomain() =
    User(
        id = this.id,
        name = this.name,
        email = this.email,
        pass = this.pass,
        sub = this.sub,
        phone = this.phone,
        photoUrl = this.photoUrl,
        verification = this.verification
    )
