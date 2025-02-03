package com.example.android_programming.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val bookId: Int? = null,
    @ColumnInfo(name = "Brand")
    val title: String,
    @ColumnInfo(name = "Model")
    val author: String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Price")
    val price: Double,
    @ColumnInfo(name = "Photo")
    val photo: Int
)
