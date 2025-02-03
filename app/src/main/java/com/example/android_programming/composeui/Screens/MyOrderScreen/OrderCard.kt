package com.example.android_programming.composeui.Screens.MyOrderScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_programming.R
import com.example.android_programming.model.Order
import com.example.android_programming.model.Book
import com.example.android_programming.vmodel.OrderViewModel
import java.util.Date

@Composable
fun OrderCard(order: Order, orderViewModel: OrderViewModel){

    val BookList = order.orderId?.let {
        orderViewModel.database.orderDao().getOrderWithBooks(
            it
        )
    }
    val bookWithOrder by BookList!!.collectAsState(null)

    val bookList: List<Book>? = bookWithOrder?.books
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.figma))
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ){
            Text("№ ${order.orderId}")
            Text("${Date(order.date)}")
            Row(){
                if (bookList != null) {
                    for(book in bookList){
                        Image(
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = book.photo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp)
                                .padding(0.dp, 10.dp, 10.dp, 10.dp)
                        )
                    }
                }
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.figma_blue),
                    contentColor = Color.White
                ),
                onClick = {
                          orderViewModel.deleteOrder(order)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
            ) {
                Text("Cancel")
            }
        }
    }
}