package com.example.android_programming.composeui.Screens.HomeScreen.BookRecyclerView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.android_programming.R
import com.example.android_programming.model.Book
import com.google.gson.Gson

@Composable
fun CardBook(item: Book, navController: NavHostController, selectedItems: List<Book>, onItemSelected: (Book) -> Unit) {
    val maxWidth = (LocalConfiguration.current.screenWidthDp / 2).dp

    Box(
        modifier = Modifier
            .padding(4.dp)
            .widthIn(maxWidth)
            .clickable {
                val bookItemString = Gson().toJson(item)
                navController.navigate("aboutBook/${bookItemString}")
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.figma))
                .widthIn(maxWidth)
        ) {
            Image(
                painter = painterResource(id = item.photo),
                contentDescription = "image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(100.dp)
            )

            Column {
                Row(
                    modifier = Modifier
                        .widthIn(maxWidth),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    ) {
                        Row {
                            item.title?.let { Text(text = "$it ") }
                        }
                        Row {
                            item.author?.let { Text(text = it) }
                        }
                        Text(text = item.price.toString(), color = Color.Red)
                    }
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.figma_blue),
                                contentColor = Color.White
                            ),
                            onClick = {
                                onItemSelected(item)
                            },
                            modifier = Modifier
                                .size(50.dp, 30.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}
