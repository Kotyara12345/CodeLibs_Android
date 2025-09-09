package com.codelibs.core_data.mapper

import com.codelibs.core_network.dto.AddedByDTO
import com.codelibs.core_network.dto.AuthorDTO
import com.codelibs.core_network.dto.BookDTO
import com.codelibs.core_network.dto.PublisherDTO
import com.codelibs.core_network.dto.RubricDTO
import com.codelibs.core_network.dto.BooksResponse
import com.codelibs.core_domain.model.book.AddedBy
import com.codelibs.core_domain.model.book.Author
import com.codelibs.core_domain.model.book.Book
import com.codelibs.core_domain.model.book.Publisher
import com.codelibs.core_domain.model.book.Rubric

fun BooksResponse.toBookList(): List<Book> {
    return results.map { it.toDomain() }
}

private fun BookDTO.toDomain(): Book {
    return Book(
        id = id,
        rubrics = rubrics.map { it.toDomain() },
        authors = authors.map { it.toDomain() },
        publisher = publisher.toDomain(),
        langCategory = langCategory,
        fileFormat = fileFormat,
        addedBy = addedBy.toDomain(),
        rating = rating,
        isFavorited = isFavorited,
        title = title,
        url = url,
        yearRelease = yearRelease,
        translator = translator,
        content = content,
        tableOfContents = tableOfContents,
        pagesNumber = pagesNumber,
        image = image,
        bookFile = bookFile,
        fileSlug = fileSlug,
        addFiles = addFiles
    )
}

private fun RubricDTO.toDomain(): Rubric {
    return Rubric(
        id = id,
        name = name,
        slug = slug,
        description = description
    )
}

private fun AuthorDTO.toDomain(): Author {
    return Author(
        id = id,
        name = name,
        description = description,
        url = url,
        image = image
    )
}

private fun PublisherDTO.toDomain(): Publisher {
    return Publisher(
        id = id,
        name = name,
        description = description,
        url = url,
        image = image
    )
}

private fun AddedByDTO.toDomain(): AddedBy {
    return AddedBy(
        id = id,
        username = username
    )
}