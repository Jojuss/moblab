package com.example.android_programming.vmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.android_programming.App
import com.example.android_programming.R
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.model.Book
import kotlinx.coroutines.launch

class BookViewModel(val database: AppDatabase): ViewModel() {
    var title = mutableStateOf("")
    val author = mutableStateOf("")
    val description = mutableStateOf("")
    val price = mutableStateOf("")
    val photo = mutableStateOf(R.drawable.img)
    val BookList = database.bookDao().getAllBooks()
    var book: Book? = null

    fun insertBook() = viewModelScope.launch {
        val book = Book(
            title = title.value,
            author = author.value,
            description = description.value,
            price = price.value.toDouble(),
            photo = photo.value
            )
        database.bookDao().insert(book)
    }

    fun deleteBook(book :  Book) = viewModelScope.launch {
        database.bookDao().delete(book)
    }

    fun getBookById(id: Int) = viewModelScope.launch {
        database.bookDao().getBookById(id)
    }

    fun UpdateBook(book: Book) = viewModelScope.launch {
        database.bookDao().update(book)
    }

    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return BookViewModel(database) as T
            }
        }
    }
}