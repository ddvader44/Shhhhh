package com.ddvader44.shhhhh.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ddvader44.shhhhh.R
import kotlinx.android.synthetic.main.fragment_book_list.*


class BookList : Fragment(R.layout.fragment_book_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_bookList_to_createReturnItem)
        }
    }
}