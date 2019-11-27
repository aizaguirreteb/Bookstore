package es.aizaguirre.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.Catalog
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        var extra = intent.extras
        var book = extra?.get("ITEM_PULSADO") as Book

        txtBookTitle.text = book.title
        txtDescription.text = book.description
        txtAuthor.text = book.authors
        Glide
            .with(baseContext)
            .load(book.cover)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageCover)


    }
}
