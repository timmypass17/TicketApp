package com.example.ticketapp.ui.dashboard

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun DashBoardScreen(
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TicketApp") },
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