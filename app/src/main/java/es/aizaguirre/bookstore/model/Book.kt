package es.aizaguirre.bookstore.model

import java.time.LocalDate

data class Book (
    val title : String,
    val cover : String,
    val isbn : String,
    val authors : String,
    val editorial : String,
    val binding : String,
    val date : LocalDate,
    val numberOfPages : Int,
    val price : Double,
    val description : String
){
    override fun toString(): String {
        return title
    }
}