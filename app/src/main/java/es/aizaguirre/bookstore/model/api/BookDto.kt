package es.aizaguirre.bookstore.model.api

data class BookDto (
    val title : String,
    val cover : String,
    val isbn : String,
    val authors : String,
    val editorial : String,
    val binding : String,
    val date : String,
    val numberOfPages : Int,
    val price : Double,
    val description : String,
    val type : String
)