package es.aizaguirre.bookstore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import es.aizaguirre.bookstore.R
import es.aizaguirre.bookstore.model.Book

class BookAdapter (val context: Context, val listofBooks : MutableList<Book>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View? = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.booklist_item_layout, null)

            val viewHolder = ViewHolder()
            viewHolder.imageView = view.findViewById<ImageView>(R.id.imageViewCover)
            viewHolder.textViewTitle = view.findViewById<TextView>(R.id.textViewItemTitle)
            viewHolder.textViewAuthors = view.findViewById<TextView>(R.id.textViewAuthors)
            viewHolder.textViewPrice = view.findViewById<TextView>(R.id.textViewPrice)
            view.tag = viewHolder
        }

        val book = listofBooks[position]

        val viewHolder = view?.tag as ViewHolder

        Glide
            .with(context)
            .load(book.cover)
             .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.imageView)

        viewHolder.textViewTitle.setText(book.title)
        viewHolder.textViewAuthors.setText(book.authors)
        viewHolder.textViewPrice.setText(book.price.toString())

            return view

    }

    override fun getItem(position: Int): Any {
        return listofBooks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listofBooks.size
    }

    private class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var textViewTitle: TextView
        lateinit var textViewAuthors: TextView
        lateinit var textViewPrice: TextView
    }

}
