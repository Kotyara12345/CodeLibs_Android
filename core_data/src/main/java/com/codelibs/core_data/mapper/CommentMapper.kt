package com.codelibs.core_data.mapper

import com.codelibs.core_domain.model.Comment
import com.codelibs.core_network.dto.CommentDTO
import java.time.OffsetDateTime

fun CommentDTO.toDomain(): Comment {
    return Comment(
        id = id,
        user = user.toDomain(),
        content = content,
        createdAt = OffsetDateTime.parse(createdAt),
        updatedAt = OffsetDateTime.parse(updatedAt),
        answerTo = answerTo,
        isActive = isActive
    )
}