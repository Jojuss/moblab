package com.example.android_programming.composeui.Screens.AdminPanel

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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.R
import com.example.android_programming.model.Book
import com.example.android_programming.vmodel.BookViewModel
import com.google.gson.Gson

@Composable
fun CardBookForChange(item: Book, navController: NavHostController, bookViewModel: BookViewModel = viewModel(factory = BookViewModel.factory)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.figma)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = item.photo),
            contentDescription = "image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(70.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            item.title?.let { Text(text = it, fontSize = 20.sp) }
            Text(text = "${item.price} $", color = Color.Red, fontSize = 16.sp)
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.figma_blue),
                contentColor = Color.White
            ),
            onClick = {
                bookViewModel.title.value = item.title ?: ""
                bookViewModel.author.value = item.author ?: ""
                bookViewModel.description.value = item.description ?: ""
                bookViewModel.price.value = item.price.toString()
                val bookItemString = Gson().toJson(item)
                navController.navigate("changeBook/${bookItemString}") },
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Create, contentDescription = "change")
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.figma_blue),
                contentColor = Color.White
            ),
            onClick = {
                  bookViewModel.deleteBook(item)
            },
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
        }
    }
}
