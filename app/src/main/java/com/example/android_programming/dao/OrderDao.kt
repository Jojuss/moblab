package com.example.android_programming.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderBook
import com.example.android_programming.model.OrderWithBooks
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert
    suspend fun createOrder(order: Order): Long

    @Insert
    suspend fun insertOrderBook(orderBook: OrderBook)

    @Query("SELECT * FROM 'Order' WHERE orderId = :id")
    fun getOrderWithBooks(id: Int): Flow<OrderWithBooks>

    @Query("SELECT * FROM `Order`")
    fun getAllOrder(): Flow<List<Order>>

    @Delete
    suspend fun delete(order: Order)
}