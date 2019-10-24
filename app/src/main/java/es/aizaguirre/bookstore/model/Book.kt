package es.aizaguirre.bookstore.model

data class Book (
    val isbn : String,
    val title : String,
    val description : String
){
    override fun toString(): String {
        return title
    }
}