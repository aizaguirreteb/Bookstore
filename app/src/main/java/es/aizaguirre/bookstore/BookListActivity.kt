package es.aizaguirre.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import es.aizaguirre.bookstore.adapters.BookAdapter
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.Catalog

class BookListActivity : AppCompatActivity() {

    lateinit var bookListView : ListView
    lateinit var adapter : BookAdapter
    val catalog : Catalog = Catalog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        Log.i("info", "1")

        val book1 = Book("Libro 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTXv0U-c4Q7ZZTlaovG-HMbZEm0xgx_amyuJL1PvkTJaRBRAb18",
            "123456789", "Yo misma", "Anaya", "Tapa dura",
            "10-04-91", 250, 20.55, "Es muy bonito", "Digital")
        val book2 = Book("Libro 2", "https://s3.amazonaws.com/librero/books/nueva-portada/portada-libro-9789585464674-activa-tu-ritmo-biologico-panda-satchin-grijalbo-bogota-librero.jpg",
            "123456", "Jose Guerrero", "Anaya", "Tapa dura",
            "10-04-91", 250, 20.55, "Es muy bonito", "Digital")

        val book3 = Book("Libro 3", "https://i.pinimg.com/474x/d0/24/4a/d0244af7845f5e7c82e3cf4fbc06dced.jpg",
            "123456", "Juan Carlos Chica", "Anaya", "Tapa dura",
            "10-04-91", 250, 20.55, "Es muy bonito", "Digital")

        catalog.addBook(book1)
        catalog.addBook(book2)
        catalog.addBook(book3)


        bookListView = findViewById(R.id.bookListView)
        Log.i("info", "2")

        adapter = BookAdapter(this, catalog.books)
        Log.i("info", "3")

        bookListView.adapter = adapter
        Log.i("info", "4")

        bookListView.setOnItemClickListener { parent, view, position, id ->
            Log.i("PARENT", parent.toString())
            Log.i("VIEW", view.toString())
            Log.i("POSITION", position.toString())
            Log.i("id", id.toString())
            var intent = Intent(baseContext, BookDetail::class.java)
            intent.putExtra(ITEM_PULSADO, position)
            startActivity(intent)
        }






    }
    companion object{
        var ITEM_PULSADO = "ITEM_PULSADO"
    }
}
