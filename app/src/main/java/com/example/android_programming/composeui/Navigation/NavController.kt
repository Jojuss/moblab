package com.example.android_programming.composeui.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_programming.composeui.Screens.AdminPanel.AddPanel
import com.example.android_programming.composeui.Screens.AdminPanel.AdminPanel
import com.example.android_programming.composeui.Screens.AdminPanel.ChangePanel
import com.example.android_programming.composeui.Screens.AdminPanel.ChangeBook
import com.example.android_programming.composeui.Screens.HomeScreen.AboutBook
import com.example.android_programming.composeui.Screens.HomeScreen.HomeScreen
import com.example.android_programming.composeui.Screens.MyOrderScreen.MyOrderScreen
import com.example.android_programming.composeui.Screens.OrderScreen.OrderScreen
import com.example.android_programming.composeui.Screens.ProfileScreen.Profile.Person
import com.example.android_programming.composeui.Screens.ProfileScreen.Profile.ProfileScreen
import com.example.android_programming.composeui.Screens.ProfileScreen.SignIn.LoginScreen
import com.example.android_programming.composeui.Screens.ProfileScreen.SignUp.SignUpScreen
import com.example.android_programming.model.Book
import com.example.android_programming.vmodel.OrderViewModel
import com.google.gson.Gson

@Composable
fun NavController(navController: NavHostController){
    var orderViewModel: OrderViewModel = viewModel(factory = OrderViewModel.factory)
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route
    ){
        composable(NavItem.Home.route){
            HomeScreen(navController, orderViewModel)
        }
        composable(NavItem.MyOrder.route){
            MyOrderScreen(orderViewModel)
        }
        composable(NavItem.Order.route){
            OrderScreen(orderViewModel, navController)
        }
        composable(NavItem.Profile.route){
            ProfileScreen(navController)
        }
        composable(NavItem.SignIn.route){
            LoginScreen(navController)
        }
        composable(NavItem.SignUp.route){
            SignUpScreen(navController)
        }
        composable(NavItem.Person.route){
            Person(navController)
        }
        composable(NavItem.AdminPanel.route){
            AdminPanel(navController)
        }
        composable(NavItem.AddPanel.route){
            AddPanel()
        }
        composable(NavItem.ChangePanel.route){
            ChangePanel(navController)
        }
        composable(NavItem.AboutBook.route) { backStackEntry ->
            val bookItemString = backStackEntry.arguments?.getString("bookItem")
            val bookItem = Gson().fromJson(bookItemString, Book::class.java)
            bookItem?.let { AboutBook(it, onBackClick = {
                navController.navigateUp() })
            }
        }
        composable(NavItem.ChangeBook.route) { backStackEntry ->
            val bookItemString = backStackEntry.arguments?.getString("bookItem")
            val bookItem = Gson().fromJson(bookItemString, Book::class.java)
            bookItem?.let { ChangeBook(it, onBackClick = {
                navController.navigateUp() })
            }
        }
    }
}