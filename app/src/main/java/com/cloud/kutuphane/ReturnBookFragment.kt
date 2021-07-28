package com.cloud.kutuphane

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_book_introduction.*
import kotlinx.android.synthetic.main.fragment_return_book.*


class ReturnBookFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    var userId = 0
    var borrowedBooks = ArrayList<String>()
    var boorwedBookId = 0
    var returnBook = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_return_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            sharedPreferences = it.getSharedPreferences(
                "com.cloud.kutuphane",
                Context.MODE_PRIVATE
            )

            userId = sharedPreferences.getInt("userId", 0)

            getBorrowedBooks(it)
            val arrayAdapter = ArrayAdapter(it,android.R.layout.simple_spinner_item,borrowedBooks)

            spinner_borrowed.adapter = arrayAdapter
            spinner_borrowed.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    returnBook = borrowedBooks[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            button_returnabook.setOnClickListener{
                context?.let {
                    returnBook(returnBook,it)

                }
            }

        }


    }

    fun getBorrowedBooks(context: Context) {
        borrowedBooks.clear()

        try{
            val db = context.openOrCreateDatabase("borrowed",
                AppCompatActivity.MODE_PRIVATE, null)

            val cursor = db.rawQuery("SELECT * FROM borrowed where userId = ?", arrayOf(userId.toString()))
            val bookIdColumnIndex = cursor.getColumnIndex("bookId")
            while(cursor.moveToNext()){
                boorwedBookId = cursor.getInt(bookIdColumnIndex)
                try {
                    val db = context.openOrCreateDatabase("books", Context.MODE_PRIVATE, null)
                    val cursor = db.rawQuery("SELECT * FROM books where id = ?", arrayOf(boorwedBookId.toString()))
                    val bookNameColumnIndex = cursor.getColumnIndex("bookName")
                    while (cursor.moveToNext()) {
                        borrowedBooks.add(cursor.getString(bookNameColumnIndex))
                    }
                    cursor.close()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            cursor.close()

        }catch (e : Exception){
            e.printStackTrace()

        }
    }

    fun returnBook(bookName : String, context: Context){


    }

}