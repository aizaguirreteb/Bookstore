package es.aizaguirre.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() , View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnCatalog = findViewById<Button>(R.id.buttonCatalog)
        var btnAddBookMain = findViewById<Button>(R.id.buttonAddBook)

        btnCatalog.setOnClickListener( this)
        btnAddBookMain.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        if(v.id == R.id.buttonCatalog){
            val intent = Intent(baseContext, BookListActivity::class.java)
            startActivity(intent)
        }
        if(v.id == R.id.buttonAddBook){
            val intent = Intent(baseContext, AddBookForm::class.java)
            startActivity(intent)
        }

    }
}
