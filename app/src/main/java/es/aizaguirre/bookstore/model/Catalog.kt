package es.aizaguirre.bookstore.model

object Catalog{

    val books = mutableListOf<Book>()

    fun addBook (newBook : Book): Boolean{
        return books.add(newBook)
    }

}