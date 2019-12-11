package es.aizaguirre.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.BookViewModel
import es.aizaguirre.bookstore.model.Catalog
import es.aizaguirre.bookstore.model.Resource
import es.aizaguirre.bookstore.model.api.BookDto
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_add_book_form.*
import kotlinx.android.synthetic.main.activity_book_detail.*
import java.util.*

class AddBookForm : Fragment(), View.OnClickListener{
    val bindingArray = arrayOf("Ebook", "Rústica", "Tapa blanda", "Cartoné", "Grapado")
    lateinit var buttonInsert: Button
    var idBook = -1

    private val bookViewModel: BookViewModel by lazy {
        ViewModelProviders.of(this).get(BookViewModel::class.java)
    }

    var updating = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_add_book_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var editBook : Book

        if(arguments != null){
            editBook = arguments!!.get("ITEM_PULSADO") as Book
            setBook(editBook)
            btnAdd.setText("UPDATE")
            textFormTitle.text = "EDIT A BOOK"
            updating = true
            idBook = editBook.id
        }
        /*val textNumBooks = view.findViewById<TextView>(R.id.textViewNumBooks)
        textNumBooks.setText("${} books")*/

        //Setting onclick listener to button 'Insert'
        buttonInsert = view.findViewById<Button>(R.id.btnAdd) as Button
        buttonInsert.setOnClickListener(this)

        //Setting listener to button clearFindViewByIdCache
        val buttonClear = view.findViewById<Button>(R.id.buttonClear)
        buttonClear.setOnClickListener(this)

        //Array of values for binding

        //Array of editorial values
        val editorialArray = arrayOf("Anaya", "Mac Graw Hill", "Oreilly", "Apress", "Manning", "Pretince Hall", "Rama")

        //ArrayAdapter for editorial
        val editorialAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, editorialArray)
        val editorialAutocomplete = view.findViewById<AutoCompleteTextView>(R.id.spinnerEditorial)
        editorialAutocomplete.setAdapter(editorialAdapter)

        //ArrayAdapter for Binding spinner
        val bindingAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, bindingArray)
        val bindingSpinner = view.findViewById<Spinner>(R.id.spinnerBinding)
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





    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(BUNDLE_LISTA, lista)
        super.onSaveInstanceState(outState)

    }*/




    override fun onClick(view: View?) {
        val buttonClicked = view

        if(buttonClicked?.id == R.id.btnAdd && updating == false ){
            var mensaje : String = ""
            when{
                !checkFieldsNotEmpty() -> mensaje = "PLEASE FILL ALL FIELDS"
                !checkISBNLength() -> mensaje = "ISBN NUMBER NOT VALID"
                !checkPrice() -> mensaje = "PRICE NOT VALID"
                !checkPages() -> mensaje = "NUMBER OF PAGES NOT VALID"
                !checkDate() -> mensaje = "DATE NOT VALID"
                checkFieldsNotEmpty() && checkISBNLength() && checkPrice() && checkPages()-> {
                    val book = retrieveBook()
                    bookViewModel.addBook(book)
                    //textViewNumBooks.text = "${bookCatalog.books.size} books"
                    //mensaje = "ADDED BOOK"
                }
            }

            Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()

        } else if(buttonClicked?.id == R.id.btnAdd && updating == true){
            var mensaje : String = ""
            when{
                !checkFieldsNotEmpty() -> mensaje = "PLEASE FILL ALL FIELDS"
                !checkISBNLength() -> mensaje = "ISBN NUMBER NOT VALID"
                !checkPrice() -> mensaje = "PRICE NOT VALID"
                !checkPages() -> mensaje = "NUMBER OF PAGES NOT VALID"
                !checkDate() -> mensaje = "DATE NOT VALID"
                checkFieldsNotEmpty() && checkISBNLength() && checkPrice() && checkPages()-> {
                    val book = retrieveBook()
                    bookViewModel.updateBook(book)
                    //textViewNumBooks.text = "${bookCatalog.books.size} books"
                    //mensaje = "ADDED BOOK"
                }
            }

            Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
        }

        if(buttonClicked?.id == R.id.buttonClear){
            clearAll()
        }
    }

    private fun observeAddedBook(){
        bookViewModel.bookAddedLiveData.observe(viewLifecycleOwner, Observer{
            book ->
            if(book.status == Resource.Status.SUCCESS){
                Toast.makeText(context, "Book ${book.data.title} added to backend}", Toast.LENGTH_LONG).show()
            }
        })
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
        //val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val type = view!!.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()

        val iNumberOfPages = sNumberOfPages.toInt()
        val dPrice = sPrice.toDouble()

        var bookInserted: Book

        if(updating){
            bookInserted = Book(idBook,
                title, cover, isbn, authors, editorial, binding,
                sDate, iNumberOfPages, dPrice, description, type)
        } else {
            bookInserted = Book(1,
                title, cover, isbn, authors, editorial, binding,
                sDate, iNumberOfPages, dPrice, description, type)
        }

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

    private fun checkPrice() = editTextPrice.text.toString().toDouble() > 0

    private fun checkPages() = seekBarPages.progress.toString().toInt() > 0

    private fun checkDate() : Boolean{
        var regexDate = "[0-9]{1,2}(-|/)[0-9]{1,2}(-|/)[0-9]{2,4}".toRegex()

        return editTextDate.text.matches(regexDate)

    }

    private fun setBook(book: Book){

        editTextTitle.setText(book.title)
        editTextISBN.setText(book.isbn)
        editTextAuthors.setText(book.authors)
        editTextDescription.setText(book.description)
        editTextPrice.setText(book.price.toString())
        editTextDate.setText(book.date)
        editTextPortada.setText(book.cover)

        spinnerEditorial.setText(book.editorial)
        seekBarPages.progress = book.numberOfPages
        for( i in 0 until bindingArray.size){
            if (bindingArray[i].equals(book.binding)){
                spinnerBinding.setSelection(i)
            } else {
                spinnerBinding.setSelection(0)
            }
        }
        if(book.type.equals("Digital")){
            radioButtonDigital.isChecked = true
        } else {
            radioButtonFisico.isChecked = true
        }


    }

    /*companion object{
        private const val BUNDLE_LISTA = "BUNDLE_LISTA"

    }*/




}
