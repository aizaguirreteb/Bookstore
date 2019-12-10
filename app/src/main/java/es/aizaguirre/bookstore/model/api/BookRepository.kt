package es.aizaguirre.bookstore.model.api

import es.aizaguirre.bookstore.config.APIConfig
import es.aizaguirre.bookstore.model.Book
import es.aizaguirre.bookstore.model.BookMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookRepository {

    private val api :BookApi

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(APIConfig.API_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(BookApi::class.java)

    }

    fun getBooks(callback: BookListRepositoryCallback){
        callback.onBookLoading()
        val call = api.getAllBooks()
        call.enqueue(object : Callback<List<Book>> {
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                callback.onBookError(t.message)
            }

            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val bookResponse = response.body().orEmpty()
                callback.onResponse(bookResponse)
            }

        })
    }

    fun addBook(book: Book, callback: BookRepositoryCallback){
        val call = api.addBook(BookMapper.transformObjectBoToDto(book))
        call.enqueue(object: Callback<Book>{

            override fun onFailure(call: Call<Book>, t: Throwable) {
                callback.onBookError(t.message)
            }

            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if(response.isSuccessful){
                    val bookResponse = response.body() ?: Book(1, "", "", "",
                        "", "", "", "", 1,
                        0.0, "", "")
                    callback.onBookResponse(bookResponse)

                } else {
                    callback.onBookError(response.message())
                }
            }

        })
    }

    fun deleteBook(book: Book, callback: BookDeleteRepositoryCallback){
        val call = api.deleteBook(book.id)
        call.enqueue(object: Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onBookError(t.message)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback.onBookResponse(response.message())
            }

        })
    }

    fun updateBook(book: Book, callback: BookUpdateRepositoryCallback){
        val call = api.updateBook(book.id, book)
        call.enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onBookError(t.message)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){

                    callback.onBookResponse(book)

                } else {
                    callback.onBookError(response.message())
                }
            }

        })
    }


    interface BookUpdateRepositoryCallback{
        fun onBookResponse(book: Book)
        fun onBookError(msg: String?)
    }


    interface BookListRepositoryCallback{
        fun onResponse(books: List<Book>)
        fun onBookError(msg: String?)
        fun onBookLoading()
    }

    interface BookRepositoryCallback{
        fun onBookResponse(book: Book)
        fun onBookError(msg: String?)
    }

    interface BookDeleteRepositoryCallback{
        fun onBookResponse(msg: String?)
        fun onBookError(msg: String?)
    }
}