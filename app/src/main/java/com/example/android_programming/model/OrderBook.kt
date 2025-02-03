package com.example.android_programming.model

import androidx.room.Entity

@Entity(primaryKeys = ["orderId", "bookId"])
data class OrderBook(
    val orderId: Int,
    val bookId: Int
)