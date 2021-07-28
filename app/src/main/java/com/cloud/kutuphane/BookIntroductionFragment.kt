package com.cloud.kutuphane

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_book_introduction.*
import kotlinx.android.synthetic.main.fragment_book_list.*


class BookIntroductionFragment : Fragment() {
    var bookId = 0
    var userId = 0
    lateinit var sharedPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_introduction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let {
            sharedPreferences = it.getSharedPreferences("com.cloud.kutuphane",
                Context.MODE_PRIVATE)

            userId = sharedPreferences.getInt("userId",0)
        }


        button_borrowed.setOnClickListener {
            borrowedBook(userId,bookId)
        }


        arguments?.let {
            bookId = BookIntroductionFragmentArgs.fromBundle(it).bookId
            context?.let {
                try {
                    val db = it.openOrCreateDatabase("books", Context.MODE_PRIVATE, null)
                    val cursor = db.rawQuery("SELECT * FROM books where id = ?", arrayOf(bookId.toString()))

                    var bookImageUrl = ""
                    val artistNameColumnIndex = cursor.getColumnIndex("artistName")
                    val bookNameColumnIndex = cursor.getColumnIndex("bookName")
                    val bookImageUrlColumnIndex = cursor.getColumnIndex("bookImageUrl")
                    val releaseDateColumnIndex = cursor.getColumnIndex("releaseDate")
                    while (cursor.moveToNext()) {
                        bookImageUrl = cursor.getString(bookImageUrlColumnIndex)
                        Picasso.get().load(bookImageUrl).resize(400,0).into(imageView_book)
                        text_artistname.text = "Yazar : ${cursor.getString(artistNameColumnIndex)}"
                        text_bookname.text = "Kitap adı : ${cursor.getString(bookNameColumnIndex)}"
                        text_releasedate.text = "Kitap basım tarihi : ${cursor.getString(releaseDateColumnIndex)}"
                    }
                    cursor.close()

                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }

        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun borrowedBook(userId : Int, bookId : Int){
        context?.let {
            try {
                val database = it.openOrCreateDatabase("borrowed", Context.MODE_PRIVATE,null)
                database.execSQL("INSERT INTO borrowed (userId,bookId) values(?,?)", arrayOf(userId,bookId))


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }

}
