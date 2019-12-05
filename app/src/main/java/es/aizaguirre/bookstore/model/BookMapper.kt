package es.aizaguirre.bookstore.model

import es.aizaguirre.bookstore.model.api.BookDto

object BookMapper{
    fun transformObjectBoToDto(bo: Book): BookDto{
        return BookDto(bo.title, bo.cover, bo.isbn,
            bo.authors, bo.editorial, bo.binding, bo.date,
            bo.numberOfPages, bo.price, bo.description, bo.type)
    }
}