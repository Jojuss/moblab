package com.example.android_programming.composeui.Screens.ProfileScreen.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.R
import com.example.android_programming.vmodel.UserViewModel

@Composable
fun SignInCard(navController: NavHostController, userViewModel: UserViewModel = viewModel(factory = UserViewModel.factory)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.figma))
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            var isEmailValid by remember { mutableStateOf(true) }
            var isPasswordValid by remember { mutableStateOf(true) }

            Text(
                text = "Sign In",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
            )

            TextField(
                value = userViewModel.email.value,
                onValueChange = {
                    userViewModel.email.value = it
                    isEmailValid = userViewModel.isValidEmail(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(16.dp, 0.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                placeholder = {
                    Text(
                        text = "Email",
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
            )

            if (!isEmailValid) {
                Text(
                    text = "Invalid email format",
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = userViewModel.password.value,
                onValueChange = {
                    userViewModel.password.value = it
                    isPasswordValid = it.isNotEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(16.dp, 0.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),

                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                placeholder = {
                    Text(
                        text = "Password",
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
            )

            if (!isPasswordValid) {
                Text(
                    text = "Password is required",
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.figma_blue),
                    contentColor = Color.White
                ),
                onClick = {
                    userViewModel.authUser()
                    navController.navigate("profile")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
            ) {
                Text("Sign In")
            }
            ClickableText(
                text = AnnotatedString("You do not have an account? Register!"),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 16.dp),
                onClick = {
                    navController.navigate("signup")
                }
            )
        }
    }
}
