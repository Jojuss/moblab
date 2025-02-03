package com.example.android_programming.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class OrderWithBooks(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "bookId",
        associateBy = Junction(OrderBook::class)
    )
    val books: List<Book>
)


