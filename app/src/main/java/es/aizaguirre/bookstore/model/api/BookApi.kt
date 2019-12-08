package es.aizaguirre.bookstore.model.api

import es.aizaguirre.bookstore.model.Book
import retrofit2.Call
import retrofit2.http.*

interface BookApi {

    @GET("books")
    fun getAllBooks() : Call<List<Book>>

    @GET("/book/{id}")
    fun getBook(@Path("id") id: Int) : Call<Book>

    @POST("books")
    fun addBook(@Body book: BookDto): Call<Book>

    @DELETE("books/{id}")
    fun deleteBook(@Path("id") id: Int): Call<Void>

    @PUT("books/{id}")
    fun updateBook(@Path("id") id: Int, @Body book: BookDto): Call<Void>
}