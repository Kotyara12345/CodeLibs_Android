package com.codelibs.core_data.remote.mapper

import com.codelibs.core_data.model.book.AddedByResponse
import com.codelibs.core_data.model.book.AuthorResponse
import com.codelibs.core_data.model.book.BookResponse
import com.codelibs.core_data.model.book.PublisherResponse
import com.codelibs.core_data.model.book.RubricResponse
import com.codelibs.core_data.remote.dto.BooksResponse
import com.codelibs.core_domain.model.book.AddedBy
import com.codelibs.core_domain.model.book.Author
import com.codelibs.core_domain.model.book.Book
import com.codelibs.core_domain.model.book.Publisher
import com.codelibs.core_domain.model.book.Rubric

fun BooksResponse.toBookList(): List<Book> {
    return results.map { it.toDomain() }
}

private fun BookResponse.toDomain(): Book {
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

private fun RubricResponse.toDomain(): Rubric {
    return Rubric(
        id = id,
        name = name,
        slug = slug,
        description = description
    )
}

private fun AuthorResponse.toDomain(): Author {
    return Author(
        id = id,
        name = name,
        description = description,
        url = url,
        image = image
    )
}

private fun PublisherResponse.toDomain(): Publisher {
    return Publisher(
        id = id,
        name = name,
        description = description,
        url = url,
        image = image
    )
}

private fun AddedByResponse.toDomain(): AddedBy {
    return AddedBy(
        id = id,
        username = username
    )
}