package com.cloud.kutuphane

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_book_list.*
import java.lang.Exception

class BookListFragment : Fragment() {
    var bookNamesList = ArrayList<String>()
    var bookIdList = ArrayList<Int>()
    private lateinit var bookListAdapter : BookListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bookListAdapter = BookListRecyclerAdapter(bookNamesList,bookIdList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = bookListAdapter
        getBooks()

        super.onViewCreated(view, savedInstanceState)
    }

    fun getBooks(){
        try{
            context?.let {
                val db = it.openOrCreateDatabase("books",
                    AppCompatActivity.MODE_PRIVATE, null)
                val cursor = db.rawQuery("SELECT * FROM books",null)
                val idColumnIndex = cursor.getColumnIndex("id")
                val bookNameColumnIndex = cursor.getColumnIndex("bookName")
                bookIdList.clear()
                bookNamesList.clear()
                while(cursor.moveToNext()){
                    bookNamesList.add(cursor.getString(bookNameColumnIndex))
                    bookIdList.add(cursor.getInt(idColumnIndex))
                }
                bookListAdapter.notifyDataSetChanged()
                cursor.close()
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
    }
}