package com.codelibs.core_data.mapper

import com.codelibs.core_domain.model.User
import com.codelibs.core_network.dto.UserDTO

fun UserDTO.toDomain(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    username = username,
    sendMessages = sendMessages ?: false,
    avatarUrl = avatar,
    email = email
)