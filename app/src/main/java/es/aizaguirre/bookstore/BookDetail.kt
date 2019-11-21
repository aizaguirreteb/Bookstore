package es.aizaguirre.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.aizaguirre.bookstore.model.Catalog
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        var extra = intent.extras
        var positionBook = extra?.get("ITEM_PULSADO") as Int

        txtBookTitle.text = Catalog.books[positionBook].title
        txtDescription.text = Catalog.books[positionBook].description
        txtAuthor.text = Catalog.books[positionBook].authors
        Glide
            .with(baseContext)
            .load(Catalog.books[positionBook].cover)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageCover)


    }
}
