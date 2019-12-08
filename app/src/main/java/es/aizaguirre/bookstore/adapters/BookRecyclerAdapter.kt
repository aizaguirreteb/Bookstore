package es.aizaguirre.bookstore.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.aizaguirre.bookstore.R
import es.aizaguirre.bookstore.model.Book

class BookRecyclerAdapter (val books: List<Book>, private val onLongClickListener: View.OnLongClickListener, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_recyclerview_item, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book, itemClickListener)
    }


    inner class BookViewHolder(view: View) :RecyclerView.ViewHolder(view) {

        val imageView = itemView.findViewById<ImageView>(R.id.imageViewCoverR)
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewItemTitleR)
        val textViewAuthors = itemView.findViewById<TextView>(R.id.textViewAuthorsR)
        val textViewPrice = itemView.findViewById<TextView>(R.id.textViewPriceR)

        fun bind(book: Book, clickListener: OnItemClickListener){
            textViewTitle.text = book.title
            textViewAuthors.text = book.authors
            textViewPrice.text = book.price.toString()


        Glide
            .with(itemView.context)
            .load(book.cover)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageView)

        itemView.setOnClickListener{
            clickListener.onClicked(book)
        }

        }

    }

    interface OnLongClickListener{
        fun onLongClick(book: Book): Boolean
    }



    interface OnItemClickListener {
        fun onClicked(book: Book)
    }
}