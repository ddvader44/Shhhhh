package com.ddvader44.shhhhh.logic.repository

import androidx.lifecycle.LiveData
import com.ddvader44.shhhhh.data.models.Book
import com.ddvader44.shhhhh.logic.dao.BookDao


class BookRepository (private val BookDao: BookDao) {
    val getAllBooks: LiveData<List<Book>> = BookDao.getAllBooks()

    suspend fun addBook(Book: Book) {
        BookDao.addBook(Book)
    }

    suspend fun updateBook(Book: Book) {
        BookDao.updateBook(Book)
    }

    suspend fun deleteBook(Book: Book) {
        BookDao.deleteBook(Book)
    }

    suspend fun deleteAllBooks() {
        BookDao.deleteAll()
    }


}