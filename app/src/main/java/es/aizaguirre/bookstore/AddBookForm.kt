package es.aizaguirre.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.Catalog
import kotlinx.android.synthetic.main.activity_add_book_form.*

class AddBookForm : AppCompatActivity(), View.OnClickListener{


    var lista = ArrayList<Book>()
    val BUNDLE_LISTA = "LISTA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book_form)

        //To save list of books
      /*  savedInstanceState.let {
            lista = savedInstanceState?.getParcelableArrayList<Book>(BUNDLE_LISTA)!!
        }*/

        //Setting onclick listener to button 'Insert'
        val buttonInsert = findViewById<Button>(R.id.btnAdd) as Button
        buttonInsert.setOnClickListener(this)

        //Array of values for binding
        val bindingArray = arrayOf("Ebook", "Rústica", "Tapa blanda", "Cartoné", "Grapado")

        //Array of editorial values
        val editorialArray = arrayOf("Anaya", "Mac Graw Hill", "Oreilly", "Apress", "Manning", "Pretince Hall", "Rama")

        //ArrayAdapter for editorial
        val editorialAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, editorialArray)
        val editorialAutocomplete = findViewById<AutoCompleteTextView>(R.id.spinnerEditorial)
        editorialAutocomplete.setAdapter(editorialAdapter)

        //ArrayAdapter for Binding spinner
        val bindingAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bindingArray)
        val bindingSpinner = findViewById<Spinner>(R.id.spinnerBinding)
        bindingSpinner.adapter = bindingAdapter


        //OnProgressChangeListener for seekBar
        seekBarPages.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textViewShowNumPages.setText(p1.toString())
            }

        })
    }


 /*   override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BUNDLE_LISTA, lista)
    }*/




    override fun onClick(view: View?) {
        val buttonClicked = view

        if(buttonClicked?.id == R.id.btnAdd){
            var mensaje : String = ""
            when{
                !checkFieldsNotEmpty() -> mensaje = "PLEASE FILL ALL FIELDS"
                !checkISBNLength() -> mensaje = "ISBN NUMBER NOT VALID"
                !checkPrice() -> mensaje = "PRICE NOT VALID"
                !checkPages() -> mensaje = "NUMBER OF PAGES NOT VALID"
                checkFieldsNotEmpty() && checkISBNLength() && checkPrice() && checkPages()-> {
                    val book = retrieveBook()
                    Catalog.addBook(book)
                    lista = Catalog.books
                    textViewNumBooks.text = "${lista.size} books"
                    clearAll()
                    mensaje = "ADDED BOOK"
                }
            }

            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

        }
    }




    private fun retrieveBook() : Book {

        val title = editTextTitle.text.toString()
        val cover = editTextPortada.text.toString()
        val isbn = editTextISBN.text.toString()
        val authors = editTextAuthors.text.toString()
        val editorial = spinnerEditorial.text.toString()
        val binding = spinnerBinding.selectedItem.toString()
        val sDate = editTextDate.text.toString()
        val sNumberOfPages = seekBarPages.progress.toString()
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
        editTextDate.setText("")
        seekBarPages.progress = 0
        editTextPrice.setText("")
        editTextDescription.setText("")
    }

    private fun checkFieldsNotEmpty() : Boolean{
        var notEmpty =true
        when{
            editTextTitle.text.isEmpty() -> notEmpty = false
            editTextAuthors.text.isEmpty() -> notEmpty = false
            editTextAuthors.text.isEmpty() -> notEmpty = false
            editTextISBN.text.isEmpty() -> notEmpty = false
            editTextDate.text.isEmpty() -> notEmpty = false
            seekBarPages.progress == 0 -> notEmpty = false
            editTextPrice.text.isEmpty() -> notEmpty = false
            editTextDescription.text.isEmpty() -> notEmpty = false
            spinnerEditorial.text.isEmpty() -> notEmpty = false
        }
        return notEmpty
    }

    private fun checkISBNLength() = editTextISBN.text.length == 13

    private fun checkPrice() = editTextPrice.text.toString().toDouble() <= 0

    private fun checkPages() = seekBarPages.progress.toString().toInt() <= 0




}
