package com.example.ticketapp.ui.dashboard

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.projectDetail.user

@Composable
fun DashBoardScreen(
    user: User
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome, ${user.name}!") },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        }
    ){
        DashBoardBody()
    }
}

@Composable
fun DashBoardBody() {
    Text("DashBoard Screen")
}