package com.example.android_programming.composeui.Screens.OrderScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.android_programming.model.Book
import com.example.android_programming.vmodel.OrderViewModel

@Composable
fun ShoppingList(list : List<Book>, orderViewModel: OrderViewModel) {
    Row {
        Column {
            for(item in list){
                CardBookLike(item = item, orderViewModel)
            }
        }
    }
}