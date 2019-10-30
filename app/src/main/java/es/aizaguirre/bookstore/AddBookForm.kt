package es.aizaguirre.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.Catalog
import kotlinx.android.synthetic.main.activity_add_book_form.*
import java.time.LocalDate

class AddBookForm : AppCompatActivity(), View.OnClickListener{


    var lista = ArrayList<Book>()
    val BUNDLE_LISTA = "LISTA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book_form)

        /*//To save list of books
        savedInstanceState.let {
            lista = savedInstanceState?.getParcelableArrayList<Book>(BUNDLE_LISTA)!!
        }*/

        //Setting onclick listener to button 'Insert'
        val buttonInsert = findViewById<Button>(R.id.btnAdd) as Button
        buttonInsert.setOnClickListener(this)

    }
/*

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BUNDLE_LISTA, lista)
    }
*/



    override fun onClick(view: View?) {
        val buttonClicked = view

        if(buttonClicked?.id == R.id.btnAdd){
           val book = retrieveBook()
            Catalog.addBook(book)
            lista = Catalog.books
            textViewNumBooks.text = "${lista.size} books"
            clearAll()
        }
    }

    private fun retrieveBook() : Book {

        val title = editTextTitle.text.toString()
        val cover = editTextPortada.text.toString()
        val isbn = editTextISBN.text.toString()
        val authors = editTextAuthors.text.toString()
        val editorial = txtEditorial.text.toString()
        val binding = editTextEnc.text.toString()
        val sDate = editTextDate.text.toString()
        val sNumberOfPages = editTextPages.text.toString()
        val sPrice = editTextPrice.text.toString()
        val description = editTextDescription.text.toString()

        val iNumberOfPages = sNumberOfPages.toInt()
        val dPrice = sPrice.toDouble()

        val bookInserted = Book(
            title, cover, isbn, authors, editorial, binding,
            sDate, iNumberOfPages, dPrice, description
        )

        return bookInserted
    }

    private fun clearAll(){
        editTextTitle.setText("")
        editTextPortada.setText("")
        editTextISBN.setText("")
        editTextAuthors.setText("")
        txtEditorial.setText("")
        editTextEnc.setText("")
        editTextDate.setText("")
        editTextPages.setText("")
        editTextPrice.setText("")
        editTextDescription.setText("")
    }
}
