package com.ddvader44.shhhhh.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddvader44.shhhhh.data.models.Book
import com.ddvader44.shhhhh.logic.dao.BookDao


@Database(entities = [Book::class],version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao() : BookDao

    companion object{
        @Volatile
        private var INSTANCE: BookDatabase? = null
        fun getDatabase(context : Context):BookDatabase {
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}