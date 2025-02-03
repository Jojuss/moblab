package com.example.android_programming.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android_programming.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT*FROM Book")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM Book WHERE bookId = :id")
    suspend fun getBookById(id: Int): Book
}