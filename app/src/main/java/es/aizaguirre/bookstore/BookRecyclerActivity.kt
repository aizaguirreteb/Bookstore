package es.aizaguirre.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.aizaguirre.bookstore.adapters.BookAdapter
import es.aizaguirre.bookstore.adapters.BookRecyclerAdapter
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.BookMapper
import es.aizaguirre.bookstore.model.BookViewModel
import es.aizaguirre.bookstore.model.Catalog

class BookRecyclerActivity : Fragment() {

    private lateinit var bookListRecycler : RecyclerView
    private lateinit var adapter : BookRecyclerAdapter

    private val booksViewModel: BookViewModel by lazy {
        ViewModelProviders.of(this).get(BookViewModel::class.java)
    }
    val catalog : Catalog = Catalog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*val book1 = Book("Libro 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTXv0U-c4Q7ZZTlaovG-HMbZEm0xgx_amyuJL1PvkTJaRBRAb18",
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
        catalog.addBook(book3)*/

        var manejadorListener = object:BookRecyclerAdapter.OnItemClickListener{
            override fun onClicked(book: Book) {

                val fragment = BookDetail()
                val args = Bundle()
                args.putParcelable("ITEM_PULSADO", book)
                fragment.setArguments(args);

                fragmentManager!!
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }

        }

        var adapterRecycler = BookRecyclerAdapter(catalog.books, manejadorListener )



        bookListRecycler = view.findViewById<RecyclerView>(R.id.bookRecyclerView).apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterRecycler
        }





    }



    companion object{
        var ITEM_PULSADO = "ITEM_PULSADO"
    }
}
