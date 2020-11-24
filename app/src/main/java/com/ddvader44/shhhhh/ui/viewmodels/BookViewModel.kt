package com.ddvader44.shhhhh.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ddvader44.shhhhh.data.database.BookDatabase
import com.ddvader44.shhhhh.data.models.Book
import com.ddvader44.shhhhh.logic.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository
    val getAllBooks: LiveData<List<Book>>


    init {
        val BookDao= BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(BookDao)

        getAllBooks = repository.getAllBooks
    }

    fun addBook(Book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(Book)
        }
    }

    fun updateBook(Book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBook(Book)
        }
    }

    fun deleteBook(Book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(Book)
        }
    }

    fun deleteAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllBooks()
        }
    }


}