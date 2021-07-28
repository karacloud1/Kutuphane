package com.cloud.kutuphane

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class BookListRecyclerAdapter(val nameList: ArrayList<String>, val idList : ArrayList<Int>): RecyclerView.Adapter<BookListRecyclerAdapter.BookHolder>() {

    class BookHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): BookHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return BookHolder(view)

    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.itemView.recycler_row_text.text = nameList[position]

        holder.itemView.setOnClickListener{
            val action = BookListFragmentDirections.actionBookListFragmentToBookIntroductionFragment(idList[position])
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {

        return nameList.size

    }
}
