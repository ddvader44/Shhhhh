package com.ddvader44.shhhhh.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val book_title:String,
    val book_description: String,
    val book_startTime: String,
    val book_endTime: String
) : Parcelable

