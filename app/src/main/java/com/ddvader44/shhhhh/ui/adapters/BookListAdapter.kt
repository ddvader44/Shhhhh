package com.ddvader44.shhhhh.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ddvader44.shhhhh.R
import com.ddvader44.shhhhh.data.models.Book
import com.ddvader44.shhhhh.ui.fragments.BookListDirections
import com.ddvader44.shhhhh.utils.Calculations
import kotlinx.android.synthetic.main.recycler_book_item.view.*

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    var bookList = emptyList<Book>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                val action =
                    BookListDirections.actionBookListToUpdateReturnItem(bookList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_book_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBook = bookList[position]
        holder.itemView.tv_item_description.text = currentBook.book_description
        holder.itemView.tv_timeElapsed.text = "Time Remaining :- ${Calculations.calculateTimeBetweenDates(currentBook.book_endTime)}"
        holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentBook.book_startTime}"
        holder.itemView.tv_item_title.text = "${currentBook.book_title}"
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setData(book: List<Book>) {
        this.bookList = book
        notifyDataSetChanged()
    }
}