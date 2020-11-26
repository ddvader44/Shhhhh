package com.ddvader44.shhhhh.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddvader44.shhhhh.R
import com.ddvader44.shhhhh.data.models.Book
import com.ddvader44.shhhhh.ui.adapters.BookListAdapter
import com.ddvader44.shhhhh.ui.viewmodels.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*


class BookList : Fragment(R.layout.fragment_book_list) {
    private lateinit var bookList: List<Book>
    private lateinit var bookViewModel: BookViewModel
    private lateinit var adapter: BookListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = BookListAdapter()
        rv_books.adapter = adapter
        rv_books.layoutManager = LinearLayoutManager(context)

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        bookViewModel.getAllBooks.observe(
            viewLifecycleOwner, Observer {
                adapter.setData(it)
                bookList = it
                if (it.isEmpty()) {
                    rv_books.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
                } else {
                    rv_books.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                }
            }
        )

        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(bookList)
            swipeToRefresh.isRefreshing = false
        }

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_bookList_to_createReturnItem)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_delete -> bookViewModel.deleteAllBooks()
        }
        return super.onOptionsItemSelected(item)
    }

}