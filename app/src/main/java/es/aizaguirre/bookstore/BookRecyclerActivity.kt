package es.aizaguirre.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.aizaguirre.bookstore.adapters.BookAdapter
import es.aizaguirre.bookstore.adapters.BookRecyclerAdapter
import es.aizaguirre.bookstore.model.*
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_book_list.*


class BookRecyclerActivity : Fragment() {

    private lateinit var bookListRecycler : RecyclerView
    private lateinit var adapterRecycler : BookRecyclerAdapter

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

        val longClickListener = object : BookRecyclerAdapter.OnLongClickListener{
            override fun onLongClick(book: Book): Boolean {
                booksViewModel.deleteBook(book)
                return true
            }

        }

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

        adapterRecycler = BookRecyclerAdapter(emptyList(),longClickListener, manejadorListener )



        bookListRecycler = view.findViewById<RecyclerView>(R.id.bookRecyclerView).apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterRecycler
        }
    }

    override fun onStart() {
        super.onStart()
        observeBookList()
        observeDeleteBook()
        booksViewModel.getBooks()

    }

    private fun observeDeleteBook(){
        booksViewModel.bookDeletedLiveData.observe(viewLifecycleOwner, Observer{
            resource ->
            if(resource.status == Resource.Status.SUCCESS){

                Toast.makeText(context, "Book Deleted" , Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Unable to delete book" , Toast.LENGTH_LONG).show()

            }
        })
    }

    private fun observeBookList(){
        booksViewModel.bookListLiveData.observe(viewLifecycleOwner, Observer{
            resource ->
            when(resource.status){
                Resource.Status.SUCCESS ->{
                    adapterRecycler.books = resource.data
                    adapterRecycler.notifyDataSetChanged()
                }
                Resource.Status.ERROR -> {
                    if(resource.message != null ){
                        Toast.makeText(context, resource.message , Toast.LENGTH_LONG).show()
                    }
                }
                Resource.Status.LOADING -> {

                }
            }
        })
    }



    companion object{
        var ITEM_PULSADO = "ITEM_PULSADO"
    }
}
