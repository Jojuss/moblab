package com.example.android_programming.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android_programming.R
import com.example.android_programming.dao.OrderDao
import com.example.android_programming.dao.BookDao
import com.example.android_programming.dao.UserDao
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderBook
import com.example.android_programming.model.RoleEnum
import com.example.android_programming.model.Book
import com.example.android_programming.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Book::class, User::class, Order::class, OrderBook::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao

    companion object {
        private const val DB_NAME: String = "my-db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        suspend fun populateDatabase() {
            INSTANCE?.let { database ->
                // User
                val userDao = database.userDao()
                val user1 = User(null, "Dmitry", "Bogdanov", "bogd@mail.ru", "123", RoleEnum.Admin)
                val user2 = User(null, "Danil", "Markov", "danil@mail.ru", "123", RoleEnum.User)
                val user3 = User(null, "Viktoria", "Presnyakova", "vika@mail.ru", "123", RoleEnum.User)
                userDao.createUser(user1)
                userDao.createUser(user2)
                userDao.createUser(user3)
                // Books
                val bookDao = database.bookDao()
                val book1 = Book(null, "Harry Potter 1", "J.K. Rowling", "nice", 159.99, R.drawable.img_1)
                val book2 = Book(null, "Harry Potter 2", "J.K. Rowling", "beautiful", 169.99, R.drawable.img_2)
                val book3 = Book(null, "Moby Dick", "Classic", "amazing", 179.99, R.drawable.img_3)
                val book4 = Book(null, "The Stranger", "Classic", "normal", 189.99, R.drawable.img_4)
                bookDao.insert(book1)
                bookDao.insert(book2)
                bookDao.insert(book3)
                bookDao.insert(book4)
                // Order
            }
        }

        fun getInstance(appContext: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    appContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                populateDatabase()
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}