package com.codelibs.core_ui.utils

import com.codelibs.core_domain.model.book.Author

//fun List<Author>.toAuthorString(): String {
//    return this.joinToString(separator = ",") { author ->
//        // Убираем запятые из исходного имени
//        val cleanName = author.name.replace(",", "").trim()
//        // Преобразуем "Иванов Александр" в "Иванов А.А."
//        val parts = cleanName.split(" ")
//        if (parts.isEmpty()) {
//            ""
//        } else {
//            val lastName = parts.first()
//            val initials = parts.drop(1).joinToString(".") {
//                it.firstOrNull()?.uppercase() ?: ""
//            } + if (parts.size > 1) "." else ""
//            "$lastName $initials"
//        }
//    }

fun List<Author>.toAuthorString(): String {
    return this.joinToString(separator = ", ") { author ->
        author.name.replace(",", "").trim()
    }
}