package es.aizaguirre.bookstore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class Book (
    val title : String,
    val cover : String,
    val isbn : String,
    val authors : String,
    val editorial : String,
    val binding : String,
    val date : String,
    val numberOfPages : Int,
    val price : Double,
    val description : String
): Parcelable{
    override fun toString(): String {
        return title
    }
}