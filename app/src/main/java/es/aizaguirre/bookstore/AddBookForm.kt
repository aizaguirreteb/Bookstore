package es.aizaguirre.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.aizaguirre.bookstore.model.Book

class AddBookForm : AppCompatActivity() {

    var lista = ArrayList<Book>()
    val BUNDLE_LISTA = "LISTA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book_form)

        savedInstanceState.let {
            lista = savedInstanceState?.getParcelableArrayList<Book>(BUNDLE_LISTA)!!
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BUNDLE_LISTA, lista)
    }
}
