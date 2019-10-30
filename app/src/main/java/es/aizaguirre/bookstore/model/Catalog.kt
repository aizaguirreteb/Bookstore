package es.aizaguirre.bookstore.model

/*
Repository for a singleton object Catalog
 */
object Catalog{

    val books = ArrayList<Book>()

    fun addBook (newBook : Book): Boolean{
        return books.add(newBook)
    }

}