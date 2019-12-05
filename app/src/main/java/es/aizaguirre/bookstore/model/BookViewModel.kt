package es.aizaguirre.bookstore.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.aizaguirre.bookstore.model.api.BookRepository

class BookViewModel : ViewModel(){

    val bookListLiveData = MutableLiveData<Resource<List<Book>>>()
    val bookAddedLiveData = MutableLiveData<Resource<Book>>()
    val bookDeletedLiveData = MutableLiveData<Resource<Book>>()

    fun getBooks(){
        BookRepository.getBooks(object: BookRepository.BookListRepositoryCallback {
            override fun onResponse(books: List<Book>) {
                bookListLiveData.value = Resource.success(books)
            }

            override fun onBookError(msg: String?) {
                bookListLiveData.value = Resource.error(msg.orEmpty(), emptyList())
            }

            override fun onBookLoading() {
                bookListLiveData.value = Resource.loading(emptyList())
            }

        })
    }

}