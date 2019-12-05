package es.aizaguirre.bookstore.model

/*
Repository for a singleton object Catalog
 */
object Catalog{

    val books = mutableListOf<Book>()
    init {
        //mock()
    }

    fun addBook (newBook : Book): Boolean{
        return books.add(newBook)
    }

    /*fun mock(){
        val book1 = Book("Libro 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTXv0U-c4Q7ZZTlaovG-HMbZEm0xgx_amyuJL1PvkTJaRBRAb18",
            "123456789", "Yo misma", "Anaya", "Tapa dura",
            "10-04-91", 250, 20.55, "Es muy bonito", "Digital")
        val book2 = Book("Libro 2", "https://s3.amazonaws.com/librero/books/nueva-portada/portada-libro-9789585464674-activa-tu-ritmo-biologico-panda-satchin-grijalbo-bogota-librero.jpg",
            "123456", "Jose Guerrero", "Anaya", "Tapa dura",
            "10-04-91", 250, 20.55, "Es muy bonito", "Digital")

        val book3 = Book("Libro 3", "https://i.pinimg.com/474x/d0/24/4a/d0244af7845f5e7c82e3cf4fbc06dced.jpg",
            "123456", "Juan Carlos Chica", "Anaya", "Tapa dura",
            "10-04-91", 250, 20.55, "Es muy bonito", "Digital")

        addBook(book1)
        addBook(book2)
        addBook(book3)
    }*/

}