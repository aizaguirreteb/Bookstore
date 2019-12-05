package es.aizaguirre.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.Catalog
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var book = arguments!!.get("ITEM_PULSADO") as Book


        txtBookTitle.text = book.title
        txtDescription.text = book.description
        txtAuthor.text = book.authors
        Glide
            .with(context!!)
            .load(book.cover)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageCover)


    }



}
