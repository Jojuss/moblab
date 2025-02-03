package com.example.android_programming.composeui.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val route: String, val icon: ImageVector?){
    object Home : NavItem("home", Icons.Default.Home)
    object MyOrder : NavItem("myorder", null)
    object Order : NavItem("order", Icons.Default.ShoppingCart)
    object Profile : NavItem("profile", Icons.Default.Person)
    object SignIn : NavItem("login", null)
    object SignUp : NavItem("signup", null)
    object Person : NavItem("person", null)
    object AdminPanel : NavItem("admin", Icons.Default.Build)
    object AddPanel : NavItem("add", null)
    object ChangePanel : NavItem("change", null)
    object AboutBook : NavItem("aboutBook/{bookItem}", null)
    object ChangeBook : NavItem("changeBook/{bookItem}", null)
}