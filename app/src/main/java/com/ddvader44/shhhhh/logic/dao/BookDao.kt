package com.ddvader44.shhhhh.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ddvader44.shhhhh.data.models.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book:Book)

    @Query("SELECT * FROM book_table ORDER BY book_endTime")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()
}